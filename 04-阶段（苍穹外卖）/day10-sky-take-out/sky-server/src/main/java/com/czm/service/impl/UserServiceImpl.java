package com.czm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.czm.constant.MessageConstant;
import com.czm.dto.UserLoginDTO;
import com.czm.entity.User;
import com.czm.exception.LoginFailedException;
import com.czm.mapper.UserMapper;
import com.czm.properties.WeChatProperties;
import com.czm.service.UserService;
import com.czm.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private UserMapper userMapper;

    private static final String wxApiUrl = "https://api.weixin.qq.com/sns/jscode2session";

    @Override
    public User wxLogin(UserLoginDTO dto) {
        // 1、通过 HttpClient 构造登录凭证校验请求
        // 构造请求参数
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("appid", weChatProperties.getAppid());     // 小程序 appId
        paramMap.put("secret", weChatProperties.getSecret());   // 小程序 appSecret
        paramMap.put("js_code", dto.getCode()); // 前端通过wx.login获取
        paramMap.put("grant_type", "authorization_code");   // 授权类型
        // HttpClientUtil 工具类发送请求
        String res = HttpClientUtil.doGet(wxApiUrl, paramMap);
        log.info("--- 微信服务器 res = {}", res);
        
        // 2、解析响应结果，获取 openid
        JSONObject jsonObject = JSON.parseObject(res);
        // openid 微信小程序用户的唯一标识
        String openid = (String) jsonObject.get("openid");
        if (openid == null) {
            throw new LoginFailedException(MessageConstant.USER_NOT_LOGIN);
        }

        // 3、判断是否为新用户，根据 openid 查询 user 表
        User user = userMapper.selectByOpenid(openid);

        // 4、如果是新用户，初始化用户数据到 User 表中
        if (user == null) { // 新用户
            user = new User();
            user.setOpenid(openid);
            user.setCreateTime(LocalDateTime.now());
            user.setName(openid.substring(0, 5));
            // 保存新用户
            userMapper.insert(user);
        }

        // 5、否则，直接返回 User 对象数据
        return user;
    }
}
