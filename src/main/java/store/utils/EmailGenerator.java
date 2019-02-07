package store.utils;

import store.model.Email;

public class EmailGenerator {
    public static int uniqueId = 0;

    public Email generateEmail(String fromAddress, String toAddress) throws EmailException{
        uniqueId++;
        if (uniqueId % 7 == 0) throw new EmailException();
        return new Email(fromAddress, toAddress, uniqueId);
    }
}
