package com.disney.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class GeneroIngresadoIncorrecto extends RuntimeException{

    public GeneroIngresadoIncorrecto(String mensaje) {
        super(mensaje);
    }
}
