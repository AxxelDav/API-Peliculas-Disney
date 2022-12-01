package com.disney.util;

public enum EnumsMensajesExcepcion {
    GENERO_INGRESADO_INCORRECTO("El Genero ingresdo es Invalido."),
    PELICULA_NO_ENCONTRADA("La Pelicula buscada No existe."),
    PERSONAJE_NO_ENCONTRADO("El Personaje ingresado No existe.");


    private final String mensaje;

    EnumsMensajesExcepcion(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return this.mensaje;
    }
}
