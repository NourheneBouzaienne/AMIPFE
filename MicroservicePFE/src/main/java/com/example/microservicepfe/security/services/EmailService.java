package com.example.microservicepfe.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender; // Injection du JavaMailSender

    public void sendActivationEmail(String to, String activationCode) {
     /*   SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Activation de compte");

        message.setText("Cliquez sur le lien suivant pour activer votre compte : " + activationCode);
        javaMailSender.send(message);
    }*/
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("Activation de compte");
            //helper.setText("Cliquez sur le lien suivant pour activer votre compte : "
                    //+ "<a href=\"" + activationCode + "\">Cliquez ici</a>", true); // true indique que le contenu est du HTML
            helper.setText("Votre Code d'activation est : "
                    +  activationCode , true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            // Gérer les erreurs de messagerie
        }


    }}

