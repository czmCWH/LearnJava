package com.czm.controller.user;

import com.czm.constant.JwtClaimsConstant;
import com.czm.dto.UserLoginDTO;
import com.czm.entity.User;
import com.czm.properties.JwtProperties;
import com.czm.result.Result;
import com.czm.service.UserService;
import com.czm.utils.JwtUtil;
import com.czm.vo.EmployeeLoginVO;
import com.czm.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户端 - 用户模块
 */

@Slf4j
@RestController
@RequestMapping("/user/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 微信小程序登录
     * @param dto
     * @return
     */
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO dto) {
        // 1、调用 Service 的登录方法，获取登录的 User 对象
        User user = userService.wxLogin(dto);

        // 2、如果登录成功，则生成 jwt 令牌
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();   // claims 有效载荷，携带一些自定义信息（用户ID、username）。
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        // 3、构造 UserLoginVO 对象并返回
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();


        return Result.success(userLoginVO);
    }

    @GetMapping("/test")
    public Result test() {
        return Result.success();
    }

}
