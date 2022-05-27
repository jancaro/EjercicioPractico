package com.example.ejercicioPractico.domain.enums;

public enum Genero {
    MASCULINO("M"),
    FEMENINO("F"),
    OTRO("O");

    private final String genero;

    Genero(String genero) { this.genero = genero; }
    public String getGenero() { return genero; }

}
