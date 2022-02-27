package com.example.objetos

open class Grafica(
                    var titulo: String? = null,
                    var uniones: List<String>? = null
                  ) {

    override fun toString(): String {
        return "ToString: GRAFICA"
    }

    open fun validarDatosGrafica(){

    }

}