package com.suyash.employeemanagementsystem.Exception;

public class ServiceLogicException extends Exception {
    public ServiceLogicException() {
        super("Something went wrong. Please try again later.");
    }

    public ServiceLogicException(String message) {
        super(message);
    }
}
