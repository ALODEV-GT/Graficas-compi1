package com.example.objetos

import com.example.excepciones.MisExcepciones

class Barras(
                val TIPO: String = "BARRAS",
                titulo: String? = null,
                var ejex: List<String>? = null,
                var ejey: List<Int>? = null,
                uniones: List<String>? = null
            ): Grafica(titulo, uniones) {
    private val datosEjeX: MutableList<String> = mutableListOf();
    private val datosEjeY: MutableList<Double> = mutableListOf();


    override fun validarDatosGrafica(){
        this.datosEjeX.clear()
        this.datosEjeY.clear()

        try {
            for (i in 0 until this.uniones!!.size) {
                var par = this.uniones!![i].replace(" ","").split(",")
                var ejex = par[0].toInt()
                var ejey = par[1].toInt()
                datosEjeX.add(this.ejex!![ejex])
                datosEjeY.add(this.ejey!![ejey].toDouble())
            }

        }catch (e: Exception){
            throw MisExcepciones("Error en unir de la grafica ${super.titulo}")
        }

        println("Datos ${super.titulo} EJEX: ${this.datosEjeX}")
        println("Datos ${super.titulo} EJEY: ${this.datosEjeY}")
    }

    fun getDatosEjeX(): MutableList<String> {
        return this.datosEjeX
    }

    fun getDatosEjeY(): MutableList<Double> {
        return this.datosEjeY
    }

    override fun toString(): String {
        return "OBJETO:$TIPO ---> titulo: ${super.titulo} ejeX:$ejex ejeY:$ejey uniones:${super.uniones}"
    }


}