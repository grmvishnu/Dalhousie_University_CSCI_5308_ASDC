package com.group18.listener;

import com.group18.entity.Trip;
import com.group18.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class TripListener implements ApplicationListener<OnTripAcceptEvent> {

    @Autowired
    private JavaMailSender mailSender;

    private void tripAcceptance(final OnTripAcceptEvent event) {
        try {
            Trip trip = event.getTrip();
            User user = trip.getUser();
            final String subject = "Trip Confirmation";
            final String recipientAddress = user.getUsername();

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMsg = "<p>Dear " + user.getFirstName() + " " + user.getLastName() + ",</p>\n" +
                    "<p>Your trip has been accepted by a host. Please login to our website to view more information.</p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p>Thank you,</p>\n" +
                    "<p>BudgetSurf Team</p>";
            helper.setText(htmlMsg, true);
            helper.setTo(recipientAddress);
            helper.setSubject(subject);
            helper.setFrom("gondaliyaparth12345@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onApplicationEvent(final OnTripAcceptEvent event) {
        this.tripAcceptance(event);
    }
}

