package com.recursos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class CargaInvalidaException extends RuntimeException {

    public CargaInvalidaException(String message) {
        super(message);
    }
}
