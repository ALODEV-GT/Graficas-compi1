package com.example.objetos;

import java.io.Serializable;

public class Operacion implements Serializable {
    private String operador;
    private int Linea;
    private int Columna;
    private String ocurrencia;

    public Operacion(String operador, int Linea, int Columna, String ocurrencia) {
        this.operador = operador;
        this.Linea = Linea;
        this.Columna = Columna;
        this.ocurrencia = ocurrencia;
    }

    public String getOperador() {
        return operador;
    }

    public int getLinea() {
        return Linea;
    }

    public int getColumna() {
        return Columna;
    }

    public String getOcurrencia() {
        return ocurrencia;
    }
}
