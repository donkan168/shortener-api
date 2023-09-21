package com.example.shortener.exceptions;

public class InfraException extends BussinessException {

    private static final String PROPIEDAD_BASE = "parametros.excepciones.region";

    public InfraException(String codigoPropiedad, Object... parametrosMensaje) {
        super(String.format("%s[%s]", PROPIEDAD_BASE, codigoPropiedad), parametrosMensaje);
    }
}
