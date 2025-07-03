package com.czm.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * 登录JWT令牌工具类
 */

public class JwtUtils {
    // 自定义的签名密钥
    private static String signkey = "JGAS/Yj/eBrEPnQ+Pm2h2Ed9w3NCJAGDelGci2+LZd8=";
    // JWT 令牌过期时间，单位毫秒
    private static Long expire = 43200000L;

    /***
     * 生成 JWT 令牌
     * @param claims
     * @return
     */
    public static String generateJwt(Map<String, Object> claims) {
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256,signkey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    /**
     * 解析 JWT 令牌
     * @param jwt 令牌
     * @return 返回 JWT 的第二部分内容，即 负载 payload 中存储的内容
     */
    public static Claims parseJwt(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(signkey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }

}
