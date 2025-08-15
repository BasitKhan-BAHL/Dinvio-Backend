package com.basitk.dinvio.security;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.jwt.Claims;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.Principal;
import java.security.PublicKey;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JwtValidationFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtValidationFilter.class);

    @Inject
    TokenBlacklistService tokenBlacklistService;

    private final PublicKey publicKey;

    public JwtValidationFilter() {
        try {
            publicKey = TokenUtils.readPublicKey("/publicKey.pem");
        } catch (Exception e) {
            throw new RuntimeException("Error loading public key in JwtValidationFilter", e);
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length()).trim();

            try {
                JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                        .setExpectedIssuer("Dinvio")
                        .setExpectedAudience("using-jwt")
                        .setVerificationKey(publicKey)
                        .setRequireExpirationTime()
                        .build();

                JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
                String subject = jwtClaims.getSubject();
                String jti = jwtClaims.getJwtId();
                java.util.List<String> groups = jwtClaims.getStringListClaimValue(Claims.groups.name());

                if (subject != null && jti != null && !tokenBlacklistService.isLatestJti(subject, jti)) {
                    String errorMessage = "Token is no longer valid.";
                    String responseJson = String.format("{\"success\": \"false\", \"message\": \"%s\"}", errorMessage);
                    LOGGER.warn("Request blocked: Token with JTI {} for user {} is no longer valid.", jti, subject);
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(responseJson).build());
                    return;
                }

                SecurityContext securityContext = new SecurityContext() {
                    @Override
                    public Principal getUserPrincipal() {
                        return () -> subject;
                    }

                    @Override
                    public boolean isUserInRole(String role) {
                        return groups != null && groups.contains(role);
                    }

                    @Override
                    public boolean isSecure() {
                        return requestContext.getUriInfo().getRequestUri().getScheme().equals("https");
                    }

                    @Override
                    public String getAuthenticationScheme() {
                        return "Bearer";
                    }
                };
                requestContext.setSecurityContext(securityContext);

            } catch (Exception e) {
                String errorMessage = "Invalid token.";
                String responseJson = String.format("{\"success\": \"false\", \"message\": \"%s\"}", errorMessage);
                LOGGER.warn("Request blocked: Invalid JWT received: {}", e.getMessage());
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity(responseJson)
                        .type(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
                        .build());
            }
        }
    }
}