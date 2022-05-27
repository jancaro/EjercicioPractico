package com.example.ejercicioPractico.excepciones;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(String mensaje) {
        super(mensaje);
    }
}
