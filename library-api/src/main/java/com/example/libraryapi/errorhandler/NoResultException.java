package com.example.libraryapi.errorhandler;

public class NoResultException extends Exception {
    public NoResultException(String errorMessage) {
        super(errorMessage);
    }
}
