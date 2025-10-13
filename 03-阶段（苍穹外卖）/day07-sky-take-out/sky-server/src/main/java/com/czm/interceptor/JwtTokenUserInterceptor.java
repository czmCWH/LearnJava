package com.czm.interceptor;

import com.czm.constant.JwtClaimsConstant;
import com.czm.context.BaseContext;
import com.czm.properties.JwtProperties;
import com.czm.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * C端：jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            // ⚠️ 此处代码作用是拦截的是否是 options 请求（即预请求），表示当前服务是否是通的。
            // 如果是 预请求，直接放行！
            return true;
        }

        // 1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getUserTokenName());

        // 2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);  // claims 有效载荷，携带一些自定义信息。
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());

            // ⚠️ 将获取到的 C 端登录用户ID，存储到 ThreadLocal 中，供后续业务中使用。
            BaseContext.setCurrentId(userId);

            log.info("当前微信用户 id：{}", userId);
            // 3、通过，放行
            return true;
        } catch (Exception ex) {
            // 4、不通过，响应401状态码
            response.setStatus(401);
            return false;
        }
    }
}
