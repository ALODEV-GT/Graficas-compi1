package com.example.cargarDatos

import com.example.excepciones.MisExcepciones
import com.example.objetos.Barras
import com.example.objetos.Grafica
import com.example.objetos.Pie


class CargaDatos(entrada: String, private var graficasObj: ArrayList<Grafica> = ArrayList()) {
    private val graficas = entrada.split("&")

    init {
        this.construirGraficas()
    }

    private fun construirGraficas() {
        for (i in 0 until graficas.size - 1) {
            this.construirGrafica(graficas[i])
        }
    }

    private fun construirGrafica(definicionGrafica: String) {
        val atributos = definicionGrafica.split("%")
        val tipo = atributos[0]
        when (tipo) {
            "Barras" -> {
                val graficaBarra = Barras()
                this.construirGraficaBarras(atributos, graficaBarra)
            }
            "Pie" -> {
                val graficaPie = Pie()
                this.construirGraficaPie(atributos, graficaPie)
            }
            else -> {
                throw MisExcepciones("No se reconocio el tipo de la grafica")
            }
        }

    }

    private fun construirGraficaBarras(atributos: List<String>, graficaBarra: Barras) {
        for (i in 1 until atributos.size - 1) {
            agregarAtributoGraficaBarras(atributos[i], graficaBarra)
        }
        this.graficasObj.add(graficaBarra)
    }

    private fun construirGraficaPie(atributos: List<String>, graficaPie: Pie) {
        for (i in 1 until atributos.size - 1) {
            this.agregarAtributoGraficaPie(atributos[i], graficaPie)
        }
        this.graficasObj.add(graficaPie)
    }

    private fun agregarAtributoGraficaPie(atributos: String, graficaPie: Pie) {
        val encabezado: String = atributos.split(":").toTypedArray()[0].replace(" ", "")
        when (encabezado) {
            "titulo" -> this.agregarTitulo(atributos, graficaPie)
            "tipo" -> this.agregarTipo(atributos, graficaPie)
            "etiquetas" -> this.agregarEtiquetas(atributos, graficaPie)
            "valores" -> this.agregarValores(atributos, graficaPie)
            "total" -> this.agregarTotal(atributos, graficaPie)
            "unir" -> this.agregarUnir(atributos, graficaPie)
            "extra" -> this.agregarExtra(atributos, graficaPie)
            else -> throw MisExcepciones("$encabezado Este campo no pertenece a la grafica de Pie")
        }
    }

    private fun agregarAtributoGraficaBarras(atributos: String, graficaBarra: Barras) {
        val encabezado: String = atributos.split(":").toTypedArray()[0].replace(" ", "")
        when (encabezado) {
            "titulo" -> this.agregarTitulo(atributos, graficaBarra)
            "ejex" -> this.agregarEjeX(atributos, graficaBarra)
            "ejey" -> this.agregarEjeY(atributos, graficaBarra)
            "unir" -> this.agregarUnir(atributos, graficaBarra)
            else -> throw MisExcepciones("$encabezado Este campo no pertenece a la grafica de Barras")
        }
    }

    //Comunes
    private fun agregarTitulo(atributo: String, grafica: Grafica) {
        grafica.titulo = atributo.split(":")[1]
    }

    private fun agregarUnir(atributo: String, grafica: Grafica) {
        val uniones = atributo.split(":")[1]
        val partes: List<String> = uniones.split("#")
        val parUniniones: MutableList<String> = mutableListOf();
        for (i in 0 until (partes.size - 1)) {
            parUniniones.add(partes[i])
        }
        grafica.uniones = parUniniones
    }

    //Barras
    private fun agregarEjeX(atributo: String, grafica: Barras) {
        val pares = atributo.split(":")[1].replace("\"", "")
        val paresEjeX: List<String> = pares.split(",")
        grafica.ejex = paresEjeX
    }

    private fun agregarEjeY(atributo: String, grafica: Barras) {
        val partesString = atributo.split(":")[1]
        val partes: List<String> = partesString.split(",")
        val partesInt: MutableList<Double> = mutableListOf();
        for (element in partes) {
            partesInt.add(element.toDouble())
        }
        grafica.ejey = partesInt
    }

    //Pie
    private fun agregarTipo(atributo: String, grafica: Pie) {
        grafica.tipo = atributo.split(":")[1]
    }

    private fun agregarEtiquetas(atributo: String, grafica: Pie) {
        val etiquetas = atributo.split(":")[1].replace("\"", "")
        val etiquetasInd: List<String> = etiquetas.split(",")
        grafica.etiquetas = etiquetasInd
    }

    private fun agregarValores(atributo: String, grafica: Pie) {
        val valoresString = atributo.split(":")[1]
        val partes: List<String> = valoresString.split(",")
        val partesInt: MutableList<Double> = mutableListOf();
        for (element in partes) {
            partesInt.add(element.toDouble())
        }
        grafica.valores = partesInt
    }

    private fun agregarTotal(atributo: String, grafica: Pie) {
        grafica.total = atributo.split(":")[1].toDouble()
    }

    private fun agregarExtra(atributo: String, grafica: Pie) {
        grafica.extra = atributo.split(":")[1].replace("\"", "")
    }

    //Otros
    fun validarGraficas() {
        for (grafica in graficasObj) {
            grafica.validarDatosGrafica()
        }
    }

    fun getGraficas(): ArrayList<Grafica> {
        return this.graficasObj
    }
}

