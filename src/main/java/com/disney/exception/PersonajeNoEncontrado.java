package com.disney.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonajeNoEncontrado extends RuntimeException {
    public PersonajeNoEncontrado (String mensaje) {
        super(mensaje);
    }
}
