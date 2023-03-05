package org.example.exceptions;

public class PrimaryCuisineNotFoundException extends RuntimeException{
    public PrimaryCuisineNotFoundException(String s) {
        super(s);
    }
}
