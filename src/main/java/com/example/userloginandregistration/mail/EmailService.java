package com.example.userloginandregistration.mail;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
@Configuration
public class EmailService implements EmailSender{
    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailService.class);
    // There is a problem that this java mail is not being injected in the code
    private final JavaMailSender sender;
    @Override
    @Async // not blocking client
    public void Send(String to, String email) {
       try{
           MimeMessage mimeMessage = sender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
           helper.setText(email,true);
           helper.setTo(to);
           helper.setSubject("Confirm your email");
           helper.setFrom("hello@amogoscode.com");
           sender.send(mimeMessage);
       } catch (MessagingException e){
           LOGGER.error("failed to send email",e);
           throw new IllegalStateException("failed to send email");
       }
    }
}
