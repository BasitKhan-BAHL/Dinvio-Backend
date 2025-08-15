package com.basitk.dinvio.security;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonNumber;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

@RequestScoped
public class JwtClaimExtractor {

    @Inject
    JsonWebToken jwt;

    public int getRoleId() {
        Object roleIdClaim = jwt.getClaim("role_id");
        if (roleIdClaim instanceof JsonNumber) {
            return ((JsonNumber) roleIdClaim).intValue();
        } else if (roleIdClaim instanceof Number) {
            return ((Number) roleIdClaim).intValue();
        } else {
            throw new ClassCastException("Could not cast role_id claim to Integer: " + roleIdClaim.getClass().getName());
        }
    }

    public String getRestaurantCode() {
        return jwt.getClaim("restaurant_code");
    }

    public Long getUserId() {
        return jwt.getClaim(Claims.preferred_username.name());
    }
}

