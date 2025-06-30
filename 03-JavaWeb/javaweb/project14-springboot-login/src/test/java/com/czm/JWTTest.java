package com.czm;

import com.czm.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTest {

    /**
     * 测试JWT令牌生成
     */
    @Test
    void testGenerateJwt() {
        // 自定义信息
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "czm");

        // HS256 签名字符串必须 >= 256 bits
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[32]; // 256 bits = 32 bytes
        secureRandom.nextBytes(randomBytes);
        // 生成加密密钥
        String SECRET_KEY = Base64.getEncoder().encodeToString(randomBytes);
//        String SECRET_KEY = "5KyArPLlw";
        System.out.println("--- 签名密钥 = " + SECRET_KEY);

        // 生成 jwt 令牌
        String jwt = Jwts.builder()     // 构建令牌
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)     // 设置签名算法 + 开发者自定义密钥（解密时需要）
                .addClaims(claims)  // 添加第二部分，有效载荷
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))  // 令牌过期时间为 1 天。如果不设置，则令牌永久有效。
                .compact();

        System.out.println("--- jwt令牌 = " + jwt);

        /*
         1、正确令牌
         --- 签名密钥 = JGAS/Yj/eBrEPnQ+Pm2h2Ed9w3NCJAGDelGci2+LZd8=
         --- jwt令牌 = eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJjem0iLCJleHAiOjE3NTEzMzU2Mzl9.2DWn7uvx7iMouHiEonRyG5JwCd-NpTPIJ_bu2yEPKUs

         2、立即过期令牌
         --- 签名密钥 = u79H3L415j3eztK0F9gvqOcn9k1l4UxvdtNmON+oqeE=
          --- jwt令牌 = eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJjem0iLCJleHAiOjE3NTEyNTAzNDR9.KgXcl8sp8Oyk2zXLa0IlLIIv68u7IEFxrHjcPUUg2cM
         */
    }

    /**
     * 测试 JWT 令牌解析
     */
    @Test
    void testParseJwt() {
        // 签名密钥
        String SECRET_KEY = "UhVgHq3VJCOypk/7s/7+k4wU5w0Alx+y23dPvVKrwdM=";
        // JWT 令牌
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJjem0iLCJleHAiOjE3NTEzNTA1MDh9.9L7J7tCCMF0Q30Lv38BDtsQj2tfYodEdY_jRGILpMiQ";
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)  // 设置签名密钥
                .build()
                .parseClaimsJws(token)  // 解析带签名的JWT
                .getBody();    // 获取第二部分的有效载荷

        System.out.println("--- 解密结果 =" + claims);

        /*
          1、如果第二部分有效载荷被篡改，会报错：
          io.jsonwebtoken.security.SignatureException: JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.

          2、如果 JWT 令牌过期，会报错：
          io.jsonwebtoken.ExpiredJwtException: JWT expired 82006 milliseconds ago at 2025-06-30T02:25:44.000Z. Current time: 2025-06-30T02:27:06.006Z. Allowed clock skew: 0 milliseconds.
         */
    }
}
