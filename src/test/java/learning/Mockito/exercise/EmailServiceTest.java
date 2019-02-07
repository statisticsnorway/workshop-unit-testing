package learning.Mockito.exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.service.EmailService;
import store.utils.EmailGenerator;
import store.utils.EmailOutputter;

import static org.mockito.Mockito.mock;

public class EmailServiceTest {

    private EmailService emailService;
    private EmailOutputter emailOutputter;
    private EmailGenerator emailGenerator;

    @BeforeEach
    public void setup() {
        emailGenerator = mock(EmailGenerator.class);
        emailOutputter = mock(EmailOutputter.class);
        emailService = new EmailService(emailGenerator, emailOutputter);
    }

    @Test
    public void writeEmailUsingEmail_requestsEmailOutputterToWriteEmail() {
        //hint verify
    }

    @Test
    public void writeEmailUsingEmail_returnsTheValueReturnedByEmailOutputter() {
        //hint when.thenReturn
    }

    @Test
    public void writeEmailUsingConstructedEmail_requestsEmailOutputterToWriteTheConstructedEmail() {
        //hint verify with argument captor / eq()
    }

    @Test
    public void writeEmailUsingConstructedEmail_returnsTheValueReturnedByEmailOutputter() {
        //hint did you use any() earlier?
    }

    @Test
    public void writeEmailUsingGeneratedEmail_requestsEmailFromEmailGenerator() {
        //hint but don't use any now!!!
    }

    @Test
    public void writeEmailUsingGeneratedEmail_returnsTrue_whenEmailGeneratorThrowsEmailException() {
        //hint when.thenThrow
    }

    @Test
    public void writeEmailUsingGeneratedEmail_requestsEmailOutputterToWriteGeneratedEmail() {
        //hint when.thenReturn verify
    }

    @Test
    public void writeEmailUsingGeneratedEmail_retrunsTheValueReturnedByEmailOutputter() {
        //hint it might be as easy as any()...
    }
}
