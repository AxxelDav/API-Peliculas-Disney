package com.disney.handler;

import com.disney.exception.ExcepcionRespuestaServicio;
import com.disney.exception.GeneroIngresadoIncorrecto;
import com.disney.exception.PeliculaNoEncontrada;
import com.disney.exception.PersonajeNoEncontrado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class ExcepcionServicioHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerAllExceptions(Exception exception, WebRequest request) {
        ExcepcionRespuestaServicio respuesta = new ExcepcionRespuestaServicio(exception.getMessage(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        return new ResponseEntity<>(respuesta, respuesta.getStatus());
    }

    @ExceptionHandler(GeneroIngresadoIncorrecto.class)
    public ResponseEntity<Object> handlerGeneroIncorrecto(Exception exception, WebRequest request) {
        ExcepcionRespuestaServicio respuesta = new ExcepcionRespuestaServicio(exception.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(respuesta, respuesta.getStatus());
    }

    @ExceptionHandler(PeliculaNoEncontrada.class)
    public ResponseEntity<Object> handlerPeliculaNoEncontrada(Exception exception, WebRequest request) {
        ExcepcionRespuestaServicio respuesta = new ExcepcionRespuestaServicio(exception.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(respuesta, respuesta.getStatus());
    }

    @ExceptionHandler(PersonajeNoEncontrado.class)
    public ResponseEntity<Object> handlerPersonajeNoEncontrado(Exception exception, WebRequest request) {
        ExcepcionRespuestaServicio respuesta = new ExcepcionRespuestaServicio(exception.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(respuesta, respuesta.getStatus());
    }
}
