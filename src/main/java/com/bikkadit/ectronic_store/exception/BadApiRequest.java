package com.bikkadit.ectronic_store.exception;

public class BadApiRequest extends RuntimeException{

    public BadApiRequest(String message){
        super(message);
    }
    public BadApiRequest(){
        super("Bad api request !!");
    }

}
