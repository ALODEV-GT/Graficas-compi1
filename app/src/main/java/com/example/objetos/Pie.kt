package com.example.objetos

import com.example.excepciones.MisExcepciones
import java.io.Serializable

class Pie : Grafica, Serializable {

    val TIPO: String = "PIE"
    var etiquetas: List<String>? = null
    var valores: List<Double>? = null
    var tipo: String? = null
    var total: Double? = null
    var extra: String? = null
    private var valorTotal: Double = 0.0

    constructor(
        titulo: String,
        etiquetas: List<String>,
        valores: List<Double>,
        tipo: String,
        total: Double,
        extra: String
    ) {
        super.titulo = titulo
        super.uniones = uniones
        this.etiquetas = etiquetas
        this.valores = valores
        this.tipo = tipo
        this.total = total
        this.extra = extra
    }

    constructor() {}

    private val datosEtiquetas: MutableList<String> = mutableListOf();
    private val datosValores: MutableList<Double> = mutableListOf();

    override fun validarDatosGrafica() {
        this.datosEtiquetas.clear()
        this.datosValores.clear()

        verificarDatosCompletos()

        try {
            for (i in 0 until this.uniones!!.size) {
                var par = this.uniones!![i].replace(" ", "").split(",")
                var ejex = par[0].toDouble().toInt()
                var ejey = par[1].toDouble().toInt()
                datosEtiquetas.add(this.etiquetas!![ejex])
                datosValores.add(this.valores!![ejey].toDouble())
                this.valorTotal+=this.valores!![ejey].toDouble()
            }
            agregarExtra()
        } catch (e: Exception) {
            throw MisExcepciones("Error en unir de la grafica ${super.titulo}")
        }
    }

    private fun agregarExtra(){
        var valorExtra = 0.0
        if(tipo == "Porcentaje"){
            valorExtra = 360 - valorTotal
        }else{
            valorExtra = total!! - valorTotal
        }

        if (valorExtra > 0.0){
            datosEtiquetas.add(this.extra!!)
            datosValores.add(valorExtra)
        }
    }

    private fun verificarDatosCompletos(){
        if(super.titulo == null || super.uniones == null || this.etiquetas == null || this.valores == null){
            throw MisExcepciones("Atributos incompletos. ")
        }
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