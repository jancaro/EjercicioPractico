package com.example.ejercicioPractico.rest;

import com.example.ejercicioPractico.exceptions.TransactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {TransactionNotFoundException.class})
    public ResponseEntity<String> handlerTransactionException(Exception excepcion) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excepcion.getMessage());
    }
}
