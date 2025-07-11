package com.czm.controller;

import com.czm.entity.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HTTP 请求，登录标记方案：（现已淘汰）
 * 1、Cookie 案例；
 * 2、Session 案例；
 */

@Slf4j
@RestController
public class SessionController {

    @GetMapping("/c1")
    Result cookie1(HttpServletResponse response) {
        // 设置 Cookie ，响应 Cookie
        response.addCookie(new Cookie("login_name", "czm_admin"));
        return Result.success();
    }

    @GetMapping("/c2")
    Result cookie2(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("login_name".equals(cookie.getName())) {
                log.info("--- 获取到 Cookie = {}", cookie.getValue());
            }
        }
        return Result.success();
    }


    @GetMapping("/s1")
    Result session1(HttpSession session) {
        log.info("--- session.hashCod {}", session.hashCode());
        session.setAttribute("login_name", "czm_admin");
        return Result.success();
    }

    @GetMapping("/s2")
    Result session2(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("--- session.hashCod = {}", session.hashCode());
        Object loginUser = session.getAttribute("login_name");
        log.info("--- loginUser = {}", loginUser);
        return Result.success(loginUser);
    }


}
