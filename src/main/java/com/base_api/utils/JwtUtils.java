package com.base_api.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtils {

    private static final SecretKey key = Jwts.SIG.HS256.key().build();
    private static final Date EXPIRATION_TIME = DateUtils.addWeeks(1);

    public static String generateToken(String username, String userExternalId) {
        return Jwts.builder().subject(username).issuedAt(new Date()).expiration(EXPIRATION_TIME).signWith(key).id(
                userExternalId).compact();
    }

    public static String extractUserExternalId(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getId();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}