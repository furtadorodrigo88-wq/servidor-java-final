package com.labanta.servidorlocal.exeption;

public class ServiceNotFoundExeption extends RuntimeException {

    public ServiceNotFoundExeption(String messagem) {
        super(messagem);
    }
}
