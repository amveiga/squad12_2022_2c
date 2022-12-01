package com.recursos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class CantidadHorasInvalidasException extends RuntimeException {
    public CantidadHorasInvalidasException(String message) { super(message); }
}
