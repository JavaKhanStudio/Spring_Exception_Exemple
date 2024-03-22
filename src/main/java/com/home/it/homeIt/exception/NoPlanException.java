package com.home.it.homeIt.exception;

public class NoPlanException extends Exception {

    public NoPlanException() {
        super("Client does not yet have a plan");
    }

}
