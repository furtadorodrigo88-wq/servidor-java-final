package com.labanta.servidorlocal.exeption;

public class UsersExistenteException extends RuntimeException {
    public UsersExistenteException(String message) {
        super(message);
    }
}
