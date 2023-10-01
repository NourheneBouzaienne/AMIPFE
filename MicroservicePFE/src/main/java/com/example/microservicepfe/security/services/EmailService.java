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


    }

    public void sendCompteEmail(String to, String activationEmailContent) {
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
            helper.setText(
                    activationEmailContent , true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            // Gérer les erreurs de messagerie
        }


    }


    public void sendConfirmationEmail(String recipientEmail) {
        // Contenu du message de confirmation
        String confirmationMessage = "Cher client,\n\n" +
                "Nous vous remercions d'avoir choisi notre compagnie d'assurance pour votre souscription. Nous sommes heureux de vous compter parmi nos clients.\n\n" +
                "Votre demande de souscription a bien été enregistrée. Un de nos conseillers vous contactera sous peu pour finaliser le processus de souscription et répondre à toutes vos questions.\n\n" +
                "Si vous avez besoin d'une assistance immédiate, n'hésitez pas à nous contacter par téléphone au 216 71.268.400 ou par e-mail à courrier@assurancesami.com.\n\n" +
                "Encore une fois, merci de nous faire confiance pour assurer votre tranquillité d'esprit.\n\n" +
                "Cordialement,\nL'équipe " + "AMI Assurances";

        // Créer l'objet SimpleMailMessage et configurer les paramètres
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Confirmation de souscription");
        message.setText(confirmationMessage);

        // Envoyer l'e-mail de confirmation à l'adresse du client
        javaMailSender.send(message);
    }






}

