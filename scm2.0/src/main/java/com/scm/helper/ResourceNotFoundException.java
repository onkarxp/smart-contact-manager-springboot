package com.scm.helper;

public class ResourceNotFoundException extends RuntimeException {

    //parameterized constructor
    public ResourceNotFoundException(String message) {
        super(message);
    }

    //default constructor
    public ResourceNotFoundException() {
        super("Resource not found");
    }

}
