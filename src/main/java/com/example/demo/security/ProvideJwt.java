package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.entity.Account;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class ProvideJwt {
    @Value("secret")
    private String jwtSecret;

    @Value("64800000")
    private String jwtExpirationInMs;

    public String generateTokenForEmployee(Account auth) {
        return JWT.create()
                .withSubject(Integer.toString(auth.getAccountId()))
                .withClaim("id", auth.getAccountId())
                .withExpiresAt(new Date(System.currentTimeMillis()  + Integer.parseInt(jwtExpirationInMs)))
                .sign(Algorithm.HMAC512(jwtSecret.getBytes()));
    }

    public Integer getUserIdFromJWT (String token) {
        String id = JWT
                .require(Algorithm.HMAC512(jwtSecret.getBytes()))
                .build()
                .verify(token)
                .getSubject();

        return Integer.parseInt(id);
    }

    public String validateToken (String accessToken) {
        return JWT
                .require(Algorithm.HMAC512(jwtSecret.getBytes()))
                .build()
                .verify(accessToken)
                .getPayload();
    }

}
