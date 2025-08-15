package com.basitk.dinvio.security;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class TokenBlacklistService {

    private final Map<String, String> latestJtiByUser = new ConcurrentHashMap<>();

    public void storeLatestJti(String username, String jti) {
        latestJtiByUser.put(username, jti);
    }

    public boolean isLatestJti(String username, String jtiToCheck) {
        String latestJti = latestJtiByUser.get(username);
        return latestJti != null && latestJti.equals(jtiToCheck);
    }
}