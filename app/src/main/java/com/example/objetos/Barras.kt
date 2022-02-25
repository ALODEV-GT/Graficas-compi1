package com.example.objetos

class Barras(
                val TIPO: String = "BARRAS",
                titulo: String? = null,
                var ejex: List<String>? = null,
                var ejey: List<String>? = null,
                uniones: List<String>? = null
            ): Grafica(titulo, uniones) {

    override fun toString(): String {
        return "OBJETO:$TIPO ---> titulo: ${super.titulo} ejeX:$ejex ejeY:$ejey uniones:${super.uniones}"
    }
}