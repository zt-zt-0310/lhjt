package com.example.data.analysis.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/11/25 15:35
 */
@Component
public class JwtUtil {
    private final String secretKey = "a8615aff9172c2531af36efb50de8185"; // 用于签署和验证令牌的密钥，请替换为自己的密钥
    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

    private final long validityInMilliseconds = 3600000; // 令牌有效期一小时
    //    private final long validityInMilliseconds = 60000; // 令牌有效期一分钟
    public String generateToken(String username,String password) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(username)
                .setAudience(password)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
