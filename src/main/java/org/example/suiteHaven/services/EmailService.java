package org.example.suiteHaven.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendHtmlEmail(String email, String token) {
        String link = "http://localhost:8080/api/v1/suiteHaven/auth/verify-email?token=" + token;
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("Bitte bestätige deine Registrierung");
            message.setFrom("registration@suiteHaven.com");
            String htmlContent = String.format("""
                    <h1>Herzlich Willkommen bei SuiteHaven!</h1>
                    <p style="margin-bottom: 50px;">Bitte bestätige deine E-Mail-Adresse, um die Registrierung abzuschließen.</p>
                    <a href="%s" style="background-color: #1985A1; color: #dcdcdd; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-bottom: 50px;">E-Mail bestätigen</a>
                    <p style="margin-top: 50px;">Vielen Dank, dass du dich bei uns registriert hast!</p>
                    """, link);
            message.setContent(htmlContent, "text/html; charset=utf-8");
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
