package com.basitk.dinvio.security;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.Claims;
import org.jose4j.jwt.JwtClaims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.UUID;

@RequestScoped
public class JwtService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtService.class);

    @Inject
    TokenBlacklistService tokenBlacklistService;

    public String generateAdminToken(String username, String restaurantCode, Integer userId, Integer roleId) {
        return generateToken(username, userId, restaurantCode, roleId, Roles.ADMIN);
    }

    public String generateUserToken(String username, String restaurantCode, Integer userId, Integer roleId) {
        return generateToken(username, userId, restaurantCode, roleId, Roles.USER);
    }

    public String generateToken(String subject, Integer name, String restaurantCode, Integer roleId, String... roles) {
        try {
            JwtClaims jwtClaims = new JwtClaims();
            jwtClaims.setIssuer("Dinvio");
            String jti = UUID.randomUUID().toString();
            jwtClaims.setJwtId(jti);
            jwtClaims.setSubject(subject);
            jwtClaims.setClaim(Claims.upn.name(), subject);
            jwtClaims.setClaim(Claims.preferred_username.name(), name);
            jwtClaims.setClaim(Claims.groups.name(), Arrays.asList(roles));
            jwtClaims.setClaim("restaurant_code", restaurantCode);
            jwtClaims.setClaim("role_id", roleId);
            jwtClaims.setAudience("using-jwt");
            jwtClaims.setExpirationTimeMinutesInTheFuture(36000);

            tokenBlacklistService.storeLatestJti(subject, jti);

            String token = TokenUtils.generateTokenString(jwtClaims);
            LOGGER.info("TOKEN generated: {}", token);
            return token;
        } catch (Exception e) {
            LOGGER.info("Error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}