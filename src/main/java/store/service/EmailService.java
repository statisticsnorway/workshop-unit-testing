package store.service;

import store.model.Email;
import store.utils.EmailException;
import store.utils.EmailGenerator;
import store.utils.EmailOutputter;

public class EmailService {
    public static final String FROM_ADDRESS = "myAdress@host.com";
    private final EmailGenerator emailGenerator;
    private final EmailOutputter emailOutputter;

    public EmailService(EmailGenerator emailGenerator, EmailOutputter emailOutputter) {
        this.emailGenerator = emailGenerator;
        this.emailOutputter = emailOutputter;
    }

    public boolean writeEmailUsingEmail(Email email, String body) {
        return emailOutputter.write(email, body);
    }

    public boolean writeEmailUsingConstructedEmail(String fromAddress, String toAddress, int uniqueId, String body) {
        Email email = new Email(fromAddress, toAddress, uniqueId);
        return emailOutputter.write(email, body);
    }

    public boolean writeEmailUsingGeneratedEmail(String toAddress, String body) {
        Email email;
        try {
            email = emailGenerator.generateEmail(FROM_ADDRESS, toAddress);
        } catch (EmailException e) {
            return true;
        }
        return emailOutputter.write(email, body);
    }
}