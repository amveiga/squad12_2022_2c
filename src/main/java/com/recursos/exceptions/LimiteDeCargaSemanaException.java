package com.recursos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)

public class LimiteDeCargaSemanaException extends RuntimeException{
    public LimiteDeCargaSemanaException(String message) { super(message); }
}