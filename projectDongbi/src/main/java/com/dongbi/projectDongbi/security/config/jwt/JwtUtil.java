package com.dongbi.projectDongbi.security.config.jwt;

import com.dongbi.projectDongbi.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${spring.jwt.secret}")
    private String secretKey;

    @Value("${spring.jwt.accessToken_expiration_time}")
    private long jwtExpiration;

    @Value("${spring.jwt.refreshToken_expiration_time}")
    private long refreshTokenExpiration;

    private Claims parseClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    public Long getId(String token){
        return getClaim(token, "id", Long.class);
    }

    public String getEmail(String token){
        return getClaim(token, "email", String.class);
    }

    public String getClubname(String token){
        return getClaim(token, "clubname", String.class);
    }

    public String getRole(String token){
        return getClaim(token, "role", String.class);
    }

    //토큰의 만료 날짜를 비교
    public Boolean isExpired(String token){
        Date expiration = parseClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public String createJwtToken(User user){
        return Jwts.builder()
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration*10))
                .signWith(getSigningKey())
                .compact();
    }

    public String createRefreshToken(User user){
        return Jwts.builder()
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(getSigningKey())
                .compact();
    }

    //HS256 알고리즘 서명 키 생성
    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    @SuppressWarnings("unchecked")
    private <T> T getClaim(String token, String claimKey, Class<T> claimType) {
        Claims claims = parseClaims(token);
        return claims.get(claimKey, claimType);
    }

}
