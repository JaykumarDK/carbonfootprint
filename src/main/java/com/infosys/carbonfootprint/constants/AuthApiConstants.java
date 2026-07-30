package com.infosys.carbonfootprint.constants;

public final class AuthApiConstants {

    private AuthApiConstants() {
    }

    private static final String BASE_URL =
            "http://localhost:8080/api/auth";

    // Login
    public static final String LOGIN =
            BASE_URL + "/login";

    // Reset Password
    public static final String RESET_PASSWORD =
            BASE_URL + "/reset-password";
}
