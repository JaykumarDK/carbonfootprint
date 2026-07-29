package com.infosys.carbonfootprint.serviceimpl;
//import com.infosys.carbonfootprint.exception.EmailSendingException;
import com.infosys.carbonfootprint.exception.EmailSendingException;
import com.infosys.carbonfootprint.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendApprovalEmail(
        String toEmail,
        String username,
        String temporaryPassword) {

        try {

            SimpleMailMessage message =
                new SimpleMailMessage();

            message.setTo(toEmail);

            message.setSubject(
                "Carbon Footprint Portal - Account Approved"
            );

            message.setText(
                "Dear User,\n\n" +
                    "Your account has been approved.\n\n" +

                    "Username: " + username + "\n" +
                    "Temporary Password: " + temporaryPassword + "\n\n" +

                    "Please change your password after first login.\n\n" +

                    "Regards,\n" +
                    "Carbon Footprint Team"
            );

            mailSender.send(message);

        } catch (Exception e) {

            throw new EmailSendingException(
                "Failed to send approval email",
                e
            );
        }
    }
}
