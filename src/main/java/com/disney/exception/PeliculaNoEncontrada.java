package com.disney.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PeliculaNoEncontrada extends RuntimeException {

    public PeliculaNoEncontrada (String mensaje) {
        super(mensaje);
    }
}
