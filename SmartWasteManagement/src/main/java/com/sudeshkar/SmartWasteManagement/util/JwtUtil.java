package com.sudeshkar.SmartWasteManagement.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
	private final String SECRET= "Smart Waste Management for Srilanka -One Nation- MoTo - Building a Clean SriLanka";
    private final long EXPIRATION= 1000*60*5;
    private final SecretKey secretKey= Keys .hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(secretKey, Jwts.SIG.HS256) 
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();    
    }
    public Boolean validateToken(String token){
    try {
        extractEmail(token);
        return true;
    } catch (JwtException e) {
    return false;
    }
    }
}
