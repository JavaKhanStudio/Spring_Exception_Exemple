package com.home.it.homeIt.exception;

public class DeviceNotAllowedException extends Exception {

    public DeviceNotAllowedException() {
        super("Device is not allowed, please change plan");
    }
}
