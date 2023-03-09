package com.example.demo.errorhandler;

public class NoResultException extends Exception {
    public NoResultException(String errorMessage) {
        super(errorMessage);
    }
}
