package com.infosys.carbonfootprint.service;

public interface EmailService {

    void sendAccountCredentials(
        String email,
        String username,
        String temporaryPassword);
}
