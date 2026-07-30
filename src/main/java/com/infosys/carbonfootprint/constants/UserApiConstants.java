package com.infosys.carbonfootprint.constants;

    public final class UserApiConstants {

        private UserApiConstants() {
        }

        private static final String BASE_URL =
                "http://localhost:8080/api/users";

        // Register User
        public static final String REGISTER =
                BASE_URL + "/register";
    }

