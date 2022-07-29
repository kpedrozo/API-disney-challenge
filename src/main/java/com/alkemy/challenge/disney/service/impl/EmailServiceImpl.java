package com.alkemy.challenge.disney.service.impl;

import com.alkemy.challenge.disney.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Environment env;

    @Value("${alkemy.disney.email.sender}")
    private String emailSender;
    @Value("${alkemy.disney.email.enabled}")
    private boolean enabled;


    String type = "text/plain";
    String value = "Bienvenido/a a Alkemy Disney";
    String subject = "Alkemy Disney";



    public void sendWelcomeEmailTo(String to) {
        if (!enabled) {
            return;
        }
        String apiKey = env.getProperty("EMAIL_API_KEY");

        Content content = new Content(type, value);

        Mail mail = emailData(to, content);
        SendGrid sg = new SendGrid(apiKey);
        Request request = requestData(mail, sg);

    }

    private Mail emailData(String to, Content content) {
        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);

        Mail mail = new Mail(fromEmail, subject, toEmail, content);

        return mail;
    }

    private Request requestData(Mail mail, SendGrid sg) {
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        }
        catch (IOException ex) {
            System.out.println("Error trying to send the email");
        }
        return request;
    }


}
