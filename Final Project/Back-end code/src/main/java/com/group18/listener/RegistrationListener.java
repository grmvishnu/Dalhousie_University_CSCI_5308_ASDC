package com.group18.listener;

import com.group18.entity.User;
import com.group18.entity.VerificationToken;
import com.group18.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private VerificationTokenRepository tokenRepository;


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        try {
            this.confirmRegistration(event);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) throws MessagingException {
        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();
        createVerificationTokenForUser(user, token);
        final String subject = "Registration Confirmation";
        final String confirmationUrl = "https://main--graceful-axolotl-b72d1c.netlify.app/" + "registrationConfirm?token=" + token;

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMsg = "<p><strong>Thank you for registering your BudgetSurf account!</strong></p>\n" +
                "<p>To activate your account, please click on the link below to complete your sign up:</p>\n" +
                "<p>" + confirmationUrl + "</p>" +
                "<p>See you soon,</p>" +
                "<p>BudgetSurf Team</p>";
        helper.setText(htmlMsg, true);
        helper.setTo(user.getUsername());
        helper.setSubject(subject);
        helper.setFrom("gondaliyaparth12345@gmail.com");
        mailSender.send(mimeMessage);
    }

    public void createVerificationTokenForUser(User user, String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

}

