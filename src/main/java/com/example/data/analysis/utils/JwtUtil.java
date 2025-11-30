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

    //    private final long validityInMilliseconds = 60000; // 令牌有效期一分钟
    public String generateToken(String username,String password,Long id) {
        Date now = new Date();
        // 令牌有效期一小时
        long validityInMilliseconds = 3600000*24*7;
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(username)
                .setAudience(password)
                .setId(String.valueOf(id))
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 从JWT令牌中获取用户名
     * @param token JWT令牌字符串
     * @return 返回令牌中包含的用户名
     */
    public String getUsernameFromToken(String token) {
    // 使用JWT解析器构建器，设置签名密钥
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)    // 设置用于验证令牌的签名密钥
                .build()                // 构建JWT解析器
                .parseClaimsJws(token)  // 解析JWT令牌并获取签名 claims
                .getBody();             // 获取 claims 的主体内容

    // 返回claims中的主题（即用户名）
        return claims.getSubject();
    }

    /**
     * 从JWT令牌中获取用户名
     * @param token JWT令牌字符串
     * @return 返回令牌中包含的用户名
     */
    public String getUserIdFromToken(String token) {
        // 使用JWT解析器构建器，设置签名密钥
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)    // 设置用于验证令牌的签名密钥
                .build()                // 构建JWT解析器
                .parseClaimsJws(token)  // 解析JWT令牌并获取签名 claims
                .getBody();             // 获取 claims 的主体内容

        // 返回claims中的主题（即用户名）
        return claims.getId();
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
