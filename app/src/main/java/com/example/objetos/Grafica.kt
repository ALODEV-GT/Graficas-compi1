package com.example.objetos

import java.io.Serializable

open class Grafica: Serializable{
    var titulo: String? = null
    var uniones: List<String>? = null

    constructor(titulo: String, uniones: List<String>){
        this.titulo = titulo;
        this.uniones = uniones;
    }

    constructor()

    override fun toString(): String {
        return "ToString: GRAFICA"
    }

    open fun validarDatosGrafica(){

    }

}