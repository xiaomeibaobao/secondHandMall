package com.hzs.shop.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author 220419
 * @description
 * @date 2026/9/17
 */
@Slf4j
@Component
public class JwtUtil {
    @Value("${jwt.secret:defaultSecretKey12345678901234567890}")
    private String secret;
    @Value("${jwt.expire:604800000}")
    private Long expire;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Long userId, String username, String role) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expire);
        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("username", username)
                .claim("role", role)
                .issuedAt(now)
                .expiration(expireDate)
                .signWith(getSigningKey())
                .compact();
    }

    public boolean verify(String token) {
        try {
            getClaims(token);
            return true;
        } catch(Exception e) {
            log.warn("Token 验证失败：{}", e.getMessage());
            return false;
        }
    }

    public Long getUserId(String token) {
        return Long.parseLong(getClaims(token).getSubject());
    }

    public String getUsername(String token) {
        return getClaims(token).get("username", String.class);
    }
    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
