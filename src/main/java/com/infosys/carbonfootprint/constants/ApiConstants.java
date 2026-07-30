package com.infosys.carbonfootprint.constants;

public final class ApiConstants {

    private ApiConstants() {
    }

    private static final String BASE_URL =
            "http://localhost:9090/api/admin";

    // Dashboard
    public static final String DASHBOARD =
            BASE_URL + "/dashboard";

    // Pending Users
    public static final String PENDING_USERS =
            BASE_URL + "/pending-users";

    // Approved Users
    public static final String APPROVED_USERS =
            BASE_URL + "/approved-users";

    // Rejected Users
    public static final String REJECTED_USERS =
            BASE_URL + "/rejected-users";

    // All Users
    public static final String ALL_USERS =
            BASE_URL + "/all-users";

    // User Details
    public static final String USER_DETAILS =
            BASE_URL + "/user-details/{id}";

    // Approve User
    public static final String APPROVE_USER =
            BASE_URL + "/approve-user/{id}";

    // Reject User
    public static final String REJECT_USER =
            BASE_URL + "/reject-user/{id}";

    // Statistics
    public static final String STATISTICS =
            BASE_URL + "/statistics";

    // Logout
    public static final String LOGOUT =
            BASE_URL + "/logout";
}
