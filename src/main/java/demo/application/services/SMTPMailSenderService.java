package demo.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SMTPMailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendAResetPasswordLink(String to, String subject, String body) {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;

        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(body, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("Exception occurred while sending reset password link to mail" + e);
        }

    }

}
