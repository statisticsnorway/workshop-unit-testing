package learning.Mockito.solutions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import store.model.Email;
import store.service.EmailService;
import store.utils.EmailException;
import store.utils.EmailGenerator;
import store.utils.EmailOutputter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class EmailServiceTest {

    @Mock
    private EmailOutputter emailOutputterMock;

    @Mock
    private EmailGenerator emailGeneratorMock;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void verifyWriteEmailUsingEmailRequestsEmailOutputterToWriteEmail() {
        Email email = new Email();

        emailService.writeEmailUsingEmail(email, "body");

        verify(emailOutputterMock).write(email, "body");
    }

    @Test
    public void verifyWriteEmailUsingEmailReturnsValueReturnedByEmailOutputter() {
        Email email = new Email();

        when(emailOutputterMock.write(any(), any())).thenReturn(true);

        boolean result = emailService.writeEmailUsingEmail(email, "body");

        assertTrue(result);
    }

    @Test
    public void verifyWriteEmailUsingConstructedEmailRequestsEmailOutputterToWriteTheConstructedEmail() {
        emailService.writeEmailUsingConstructedEmail("from@me", "to@you", 1, "body");

        ArgumentCaptor<Email> argument = ArgumentCaptor.forClass(Email.class);
        verify(emailOutputterMock).write(argument.capture(), eq("body"));

        assertEquals("from@me", argument.getValue().getFromAddress());
        assertEquals("to@you", argument.getValue().getToAddress());
        assertEquals(1, argument.getValue().getUniqueId());
    }

    @Test
    public void verifyWriteEmailUsingConstructedEmailReturnsTheValueReturnedByEmailOutputter() {
        when(emailOutputterMock.write(any(), any())).thenReturn(true);

        boolean result = emailService.writeEmailUsingConstructedEmail("from@me", "to@you", 1, "body");

        assertTrue(result);
    }

    @Test
    public void verifyWriteEmailUsingGeneratedEmailRequestsEmailFromEmailGenerator() {
        emailService.writeEmailUsingGeneratedEmail("to@you", "body");

        verify(emailGeneratorMock).generateEmail(anyString(), eq("to@you"));
    }

    @Test
    public void verifyWriteEmailUsingGeneratedEmailReturnsTrue_whenEmailGeneratorThrowsEmailException() {
        when(emailGeneratorMock.generateEmail(anyString(), anyString())).thenThrow(new EmailException());

        boolean result = emailService.writeEmailUsingGeneratedEmail("to@you", "body");

        assertTrue(result);
    }

    @Test
    public void verifyWriteEmailUsingGeneratedEmailRequestsEmailOutputterToWriteGeneratedEmail() {
        Email email = new Email("from@me", "to@you", 1);
        when(emailGeneratorMock.generateEmail(anyString(), anyString())).thenReturn(email);

        emailService.writeEmailUsingGeneratedEmail("to@you", "body");

        verify(emailOutputterMock).write(email, "body");
    }

    @Test
    public void verifyWriteEmailUsingGeneratedEmailReturnsValueReturnedByEmailOutputter() {
        when(emailOutputterMock.write(any(), any())).thenReturn(true);

        boolean result = emailService.writeEmailUsingGeneratedEmail("to@you", "body");

        assertTrue(result);
    }
}
