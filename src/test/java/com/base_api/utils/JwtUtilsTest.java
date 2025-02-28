package com.base_api.utils;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtilsTest {

    @Test
    public void testGenerateToken() {
        String username = "user1";
        String userExternalId = "12345";
        String token = JwtUtils.generateToken(username, userExternalId);

        Assertions.assertNotNull(token);
        Assertions.assertFalse(token.isEmpty());

        String extractedExternalId = JwtUtils.extractUserExternalId(token);
        Assertions.assertEquals(userExternalId, extractedExternalId);
    }

    @Test
    public void testExtractUserExternalId() {
        String username = "user1";
        String userExternalId = "12345";
        String token = JwtUtils.generateToken(username, userExternalId);

        String extractedExternalId = JwtUtils.extractUserExternalId(token);
        Assertions.assertEquals(userExternalId, extractedExternalId);
    }

    @Test
    public void testValidateTokenValid() {
        String username = "user1";
        String userExternalId = "12345";
        String token = JwtUtils.generateToken(username, userExternalId);

        boolean isValid = JwtUtils.validateToken(token);
        Assertions.assertTrue(isValid);
    }

    @Test
    public void testValidateTokenInvalid() {
        String invalidToken = "invalidToken12345";

        boolean isValid = JwtUtils.validateToken(invalidToken);
        Assertions.assertFalse(isValid);
    }

    @Test
    public void testValidateTokenExpired() {
        SecretKey key = Jwts.SIG.HS256.key().build();

        // Simula un token expirado
        String username = "user1";
        String userExternalId = "12345";
        Date expiredDate = DateUtils.addDays(new Date(), -1);
        String token = Jwts.builder().subject(username)
                .issuedAt(new Date()).expiration(expiredDate)
                .signWith(key).id(userExternalId).compact();

        boolean isValid = JwtUtils.validateToken(token);
        Assertions.assertFalse(isValid);
    }
}
