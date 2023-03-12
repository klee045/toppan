package com.example.libraryapi.errorhandlers;

public class NoResultException extends Exception {
    public NoResultException(String errorMessage) {
        super(errorMessage);
    }
}
