package com.hmall.common.interceptors;

import cn.hutool.core.util.StrUtil;
import com.hmall.common.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义 SpringMVC 拦截器，用于获取登录用户信息保存到 ThreadLocal 中
 * 注意：次拦截器不需要做登录校验，因为登录校验放在官网里做了，请求能到此拦截器，说明校验通过了。
 */

public class UserInfoInterceptor implements HandlerInterceptor {

    // preHandle 在 Controller 之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1、获取登录用户信息
        String userInfo = request.getHeader("user-info");
        // 2、判断是否获取了用户信息，如果有，存入 ThreadLocal
        if (StrUtil.isNotBlank(userInfo)) {
            UserContext.setUser(Long.valueOf(userInfo));
        }
        // 3、如果没有直接放行（）
        return true;
    }

    // afterCompletion 在 Controller 之后执行，用于进行清理操作
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理用户信息
        UserContext.removeUser();
    }
}
