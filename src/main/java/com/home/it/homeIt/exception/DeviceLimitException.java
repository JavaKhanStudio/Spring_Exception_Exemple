package com.home.it.homeIt.exception;

public class DeviceLimitException extends Exception {

    public DeviceLimitException() {
        super("Too many devices on this account, please change plan");
    }

}
