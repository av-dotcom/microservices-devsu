package com.cuentaservicio.exceptions;

public class CuentaNotFoundException extends Exception {

    public CuentaNotFoundException(String mensaje) {
        super(mensaje);
    }
}
