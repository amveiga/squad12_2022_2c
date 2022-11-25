package com.recursos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class LegajoNoEncontradoException extends RuntimeException {

    public LegajoNoEncontradoException(String message) {
        super(message);
    }
}
