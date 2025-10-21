package com.czm.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT令牌 - 工具类
 */
public class JwtUtils {
    // 签名密钥
    private static final String SECRET_KEY = "JGAS/Yj/eBrEPnQ+Pm2h2Ed9w3NCJAGDelGci2+LZd8=";
    // JWT 令牌过期时间为 12个小时，单位毫秒
    private static final Long EXPIRATION_TIME = 12 * 60 * 60 * 1000L;

    /***
     * 生成 JWT 令牌
     * @param claims
     * @return
     */
    public static String generateJwt(Map<String, Object> claims) {
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
        return jwt;
    }

    /**
     * 校验（解析） JWT 令牌
     * @param jwt 令牌
     * @return 返回 JWT 的第二部分内容，即 负载 payload 中存储的内容
     */
    public static Claims parseJwt(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
