package com.infosys.carbonfootprint.serviceimpl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.infosys.carbonfootprint.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendAccountCredentials(
        String email,
        String username,
        String temporaryPassword) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Carbon Footprint Monitoring - Account Approved");

        message.setText(
            "Your registration has been approved.\n\n"
                + "Username: " + username + "\n"
                + "Temporary Password: " + temporaryPassword + "\n\n"
                + "Please change your password after your first login."
        );

        mailSender.send(message);
    }
}
