package com.neocyber.exception;

public class AlreadyExists extends RuntimeException{

    public AlreadyExists( String message ) {
        super(message);
    }

}
