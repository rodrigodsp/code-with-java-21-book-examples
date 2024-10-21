package com.example.chapter2;

public class InvalidUOMException extends RuntimeException {

    public InvalidUOMException() {
    
        super("The UOM of this entry could not be determined.");
    }
    
}
