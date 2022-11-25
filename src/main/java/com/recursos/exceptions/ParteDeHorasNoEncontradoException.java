package com.recursos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ParteDeHorasNoEncontradoException extends RuntimeException {

    public ParteDeHorasNoEncontradoException(String message) {
        super(message);
    }
}
