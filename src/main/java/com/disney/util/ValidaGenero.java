package com.disney.util;

import com.disney.entidad.Genero;
import com.disney.exception.GeneroIngresadoIncorrecto;

public class ValidaGenero {

    public static boolean validaGenero(Genero genero) {
        if(genero.getImagen() == null || genero.getNombre() == null) {
            throw new GeneroIngresadoIncorrecto(EnumsMensajesExcepcion.GENERO_INGRESADO_INCORRECTO.getMensaje());
        }

        return true;
    }
}
