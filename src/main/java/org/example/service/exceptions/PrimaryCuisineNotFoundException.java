package org.example.service.exceptions;

public class PrimaryCuisineNotFoundException extends RuntimeException{
    public PrimaryCuisineNotFoundException(String s) {
        super(s);
    }
}
