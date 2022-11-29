package com.recursos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TareaNoEncontradaException extends RuntimeException {

    public TareaNoEncontradaException(String message) {
        super(message);
    }
}