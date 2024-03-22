package com.home.it.homeIt.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("User not found.");
    }

}
