package com.example.nerdy.service.impl;

import com.example.nerdy.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;

    @Value("$(Nerdy Task)")
    private String username;

    @Override
    public void sendEmail(String emailTo , String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(username);
        simpleMailMessage.setTo(emailTo);
        simpleMailMessage.setText(message);

        mailSender.send(simpleMailMessage);
    }
}
