package com.example.objetos

import com.example.excepciones.MisExcepciones

class Pie(
            val TIPO: String = "PIE",
            titulo: String? = null,
            var etiquetas: List<String>?=null,
            var valores: List<Int>?=null,
            var tipo: String?=null,
            var total: Int?=null,
            var extra: String?=null,
            uniones: List<String>? = null
         ): Grafica(titulo,uniones) {
    private val datosEtiquetas: MutableList<String> = mutableListOf();
    private val datosValores: MutableList<Double> = mutableListOf();

    override fun validarDatosGrafica(){
        this.datosEtiquetas.clear()
        this.datosValores.clear()

        try {
            for (i in 0 until this.uniones!!.size) {
                var par = this.uniones!![i].replace(" ","").split(",")
                var ejex = par[0].toInt()
                var ejey = par[1].toInt()
                datosEtiquetas.add(this.etiquetas!![ejex])
                datosValores.add(this.valores!![ejey].toDouble())
            }

        }catch (e: Exception){
            throw MisExcepciones("Error en unir de la grafica ${super.titulo}")
        }

        println("Datos ${super.titulo} Etiquetas: ${this.datosEtiquetas}")
        println("Datos ${super.titulo} Valores: ${this.datosValores}")
    }

    override fun toString(): String {
        return "OBJETO:$TIPO ---> titulo: ${super.titulo} etiquetas:$etiquetas valores:$valores tipo:$tipo total:$total extra:$extra uniones:${super.uniones}"
    }

    fun getDatosEtiquetas(): MutableList<String> {
        return this.datosEtiquetas
    }

    fun getDatosValores(): MutableList<Double> {
        return this.datosValores
    }
}