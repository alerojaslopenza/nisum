package com.nisum.demo.service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nisum.demo.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;

@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    @PostConstruct
    protected void init(){
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String generateToken(User user){
        Map<String, Object> claims = new HashMap<>();
        claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("id", user.getId());
        Date create = new Date();
        Date expiration = new Date(create.getTime() + 3600000);
        return  Jwts.builder().setClaims(claims)
                .setIssuedAt(create)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
 
}
