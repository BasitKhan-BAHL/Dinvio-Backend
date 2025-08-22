package com.basitk.dinvio.security;

import java.util.HashMap;
import java.util.Map;

public final class Roles {
    private Roles() {}

    public static final Integer ADMIN = 1;
    public static final Integer USER = 2;

    private static final Map<Integer, String> ROLE_NAMES = new HashMap<>();

    static {
        ROLE_NAMES.put(ADMIN, "ADMIN");
        ROLE_NAMES.put(USER, "USER");
    }

    public static String getRoleName(Integer roleId) {
        return ROLE_NAMES.getOrDefault(roleId, "UNKNOWN");
    }

    public static boolean isValidRole(Integer roleId) {
        return ROLE_NAMES.containsKey(roleId);
    }
}
