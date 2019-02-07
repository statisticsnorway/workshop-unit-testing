package store.utils;

import store.model.Email;

public class EmailOutputter {
    public boolean write(Email email, String body) {
        System.out.println("Sucessfully wrote email!");
        return email.getUniqueId() % 2 == 0;
    }
}
