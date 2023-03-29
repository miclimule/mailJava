package mailJava;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class SendEmail {
    public static void main(String[] args) {
        // informations sur l'expéditeur
        String from = "medra7638@gmail.com";
        String password = "ncyofjwniazygrbi";

        // informations sur le destinataire
        String to = "miclimule@gmail.com";

        // configuration du serveur SMTP
        String host = "smtp.gmail.com";
        String port = "587";

        // propriétés pour la connexion au serveur SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // création de la session
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        try {
            // création du message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("test d'envoi");

            // création de la pièce jointe
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile("5_Sardinas_Patterson.pdf");

            // création du corps du message
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Bonjour,\n\nCi-joint un fichier PDF.\n\nCordialement,\nVotre Nom");

            // création du message multi-part
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            // envoi du message
            Transport.send(message);

            System.out.println("Le message a été envoyé avec succès.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}