package com.disney.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void simpleTextMessage(String destinatario) throws MessagingException {
        String bodyMessage = "Este es un ejemplo de correo ID=" + UUID.randomUUID().toString();

        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo("waltercleipogty@gmail.com");
        message.setTo(destinatario);
        message.setFrom("lolokopobase2@gmail.com");
        message.setSubject("HOLIIIII \uD83D\uDE00  \uD83D\uDC7E");
        message.setText(bodyMessage);

        emailSender.send(message);
    }


    public void fileTextMessage(String destinatario) throws MessagingException {
        String bodyMessage = "Este es un ejemplo de correo ID=" + UUID.randomUUID().toString();
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

//        message.setTo("waltercleipogty@gmail.com");
        message.setTo(destinatario);
        message.setFrom("lolokopobase2@gmail.com");
        message.addAttachment("subzero.JPG", new File("C:\\dev\\workspace\\pelis-disney\\subzero.JPG"));
        message.setSubject("Mensaje de Correo Electronico");
        message.setSentDate(new Date());
        message.setText("<h1>" + bodyMessage + "</h1>", true);

        this.emailSender.send(mimeMessage);
    }

}
