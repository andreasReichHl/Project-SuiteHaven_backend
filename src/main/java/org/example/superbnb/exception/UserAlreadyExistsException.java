package org.example.superbnb.exception;

import org.example.superbnb.entities.users.User;

public class UserAlreadyExistsException extends RuntimeException {
    private User user;

    public UserAlreadyExistsException(User user) {
        super("Host mit der Mail " + user.getEmail() + " exisitiert bereits!");
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
