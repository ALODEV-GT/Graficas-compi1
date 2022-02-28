package com.example.objetos

import com.example.excepciones.MisExcepciones
import java.io.Serializable

class Barras: Grafica, Serializable {
    val TIPO: String = "BARRAS"
    var ejex: List<String>? = null
    var ejey: List<Int>? = null

    constructor(ejex: List<String>, titulo: String, ejey: List<Int>, uniones: List<String>){
        super.uniones = uniones
        super.titulo = titulo
        this.ejex = ejex
        this.ejey = ejey
    }

    constructor()

    private val datosEjeX: MutableList<String> = mutableListOf();
    private val datosEjeY: MutableList<Double> = mutableListOf();

    override fun validarDatosGrafica(){
        this.datosEjeX.clear()
        this.datosEjeY.clear()

        verificarDatosCompletos()

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
    }
    private fun verificarDatosCompletos(){
        if(super.titulo == null || super.uniones == null || this.ejex == null || this.ejey == null){
            throw MisExcepciones("Atributos incompletos. ")
        }
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