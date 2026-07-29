package com.infosys.carbonfootprint.service;

public interface  EmailService {

    void sendApprovalEmail(
        String toEmail,
        String username,
        String temporaryPassword
    );
}
