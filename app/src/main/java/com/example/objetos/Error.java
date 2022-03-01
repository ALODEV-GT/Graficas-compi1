package com.example.objetos;

import java.io.Serializable;

public class Error implements Serializable {
    private String Lexema;
    private int linea;
    private int columna;
    private String tipo;
    private String descripcion;

    public Error(String Lexema, int linea, int columna, String tipo, String descripcion) {
        this.Lexema = Lexema;
        this.linea = linea;
        this.columna = columna;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public String getLexema() {
        return Lexema;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
