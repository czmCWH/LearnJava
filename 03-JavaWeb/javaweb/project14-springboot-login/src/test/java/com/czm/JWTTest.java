package com.czm;

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


        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[32]; // 256 bits = 32 bytes
        secureRandom.nextBytes(randomBytes);

        String randomString = Base64.getEncoder().encodeToString(randomBytes);

        // 生成 jwt 令牌
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, randomString)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .compact();

        System.out.println("---jwt = " + jwt);
    }
}
