package com.neoris.turnosrotativos.exceptions;

public class BadRequestException extends RuntimeException{

    public BadRequestException (String messange){

        super(messange);
    }
}
