package com.example.practica1comp1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cargarDatos.CargaDatos
import com.example.excepciones.MisExcepciones
import com.example.jcup.parser
import com.example.jlex.AnalizadorLexico
import com.example.objetos.Ejecuciones
import com.example.objetos.Error
import com.example.objetos.Grafica
import com.example.objetos.Operacion
import java.io.StringReader

class MainActivity : AppCompatActivity() {
    //Botones
    private var btnVerGraficas: Button? = null
    private var btnRepOpAritmeticos: Button? = null
    private var btnRepGraficosDefinidos: Button? = null
    private var btnRepErrores: Button? = null

    //MOSTRAR GRAFICAS
    private var graficasObj: ArrayList<Grafica>? = null
    private var ejecuciones: ArrayList<String>? = null

    //REPORTES
    //Reporte numero de graficas
    private var numGraficasPie: Int = 0
    private var numGraficasBarras: Int = 0

    //Reporte numero de operaciones
    private var operaciones: ArrayList<Operacion>? = null

    //Reporte de errores
    private var erroresLexicos: ArrayList<Error> = ArrayList()
    private var erroresSintacticos: ArrayList<Error> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setTitle(R.string.tituloApp)
        this.btnVerGraficas = findViewById(R.id.btnVerGraficas)
        this.btnRepOpAritmeticos = findViewById(R.id.btnRepOpAritmeticos)
        this.btnRepGraficosDefinidos = findViewById(R.id.btnGraficosDefinidos)
        this.btnRepErrores = findViewById(R.id.btnErrores)
        btnVerGraficas!!.isEnabled = false
        btnRepOpAritmeticos!!.isEnabled = false
        btnRepGraficosDefinidos!!.isEnabled = false
        btnRepErrores!!.isEnabled = false
    }

    fun reporteOperadoresAritmeticos(view: View) {
        val intent = Intent(this, RepOperadoresAritmeticos::class.java)
        val bundle = Bundle()
        this.operaciones!!.forEach {
            println(it.operador)
        }

        bundle.putSerializable("operaciones", this.operaciones!!)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun reporteGraficasDefinidas(view: View) {
        val intent = Intent(this, RepGraficasDefinidas::class.java)
        val bundle = Bundle()
        bundle.putSerializable("numGraficasBarras", this.numGraficasBarras)
        bundle.putSerializable("numGraficasPie", this.numGraficasPie)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun reporteErrores(view: View) {
        val intent = Intent(this, RepErrores::class.java)
        var bundle: Bundle? = null

        bundle = Bundle()
        bundle.putSerializable("erroresLexicos", this.erroresLexicos!!)
        bundle.putSerializable("erroresSintacticos", this.erroresSintacticos!!)
        intent.putExtras(bundle)

        startActivity(intent)
    }

    fun verGraficas(view: View) {
        val intent = Intent(this, Graficas::class.java)
        val bundle = Bundle()
        bundle.putSerializable("graficas", this.graficasObj!!)
        bundle.putSerializable("ejecuciones", this.ejecuciones!!)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun analizar(view: View) {
        val entrada = findViewById<EditText>(R.id.etEntrada)
        val sr = StringReader(entrada.text.toString())
        val analizadorLexico = AnalizadorLexico(sr)
        val analizadorSintactico = parser(analizadorLexico)

        try {
            analizadorSintactico.parse()
            Toast.makeText(
                applicationContext,
                "Analisis completado",
                Toast.LENGTH_SHORT
            ).show()
            val cargarDatos = CargaDatos(analizadorSintactico.definiciones)

            if (analizadorSintactico.errores.size > 0 || analizadorLexico.errores.size > 0){
                //PARA REPORTE DE ERRORES
                this.erroresSintacticos = analizadorSintactico.errores
                this.erroresLexicos = analizadorLexico.errores
                desactivarActivarBotonesAnalisisIncorrecto()
            }else{
                //PARA GRAFICAR
                this.graficasObj = cargarDatos.getGraficas()
                cargarDatos.validarGraficas()
                val obEjecuciones = Ejecuciones(analizadorSintactico.ejecuciones)
                this.ejecuciones = obEjecuciones.ejecuciones

                // PARA REPORTE DE OPERACIONES
                this.operaciones = analizadorSintactico.operaciones

                //PARA REPORTE DE APARICION DE GRAFICAS
                this.numGraficasBarras = analizadorSintactico.numGraficasBarras()
                this.numGraficasPie = analizadorSintactico.numGraficasPie()
                desactivarActivarBotonesAnalisisCorrecto()
            }

        } catch (e: MisExcepciones) {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            desactivarActivarBotonesAnalisisIncorrecto()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Toast.makeText(applicationContext, "Error sintactico", Toast.LENGTH_SHORT).show()
            desactivarActivarBotonesAnalisisIncorrecto()
        }
    }

    private fun desactivarActivarBotonesAnalisisCorrecto() {
        btnRepErrores!!.isEnabled = false
        this.btnVerGraficas!!.isEnabled = true
        this.btnRepOpAritmeticos!!.isEnabled = true
        this.btnRepGraficosDefinidos!!.isEnabled = true
    }

    private fun desactivarActivarBotonesAnalisisIncorrecto() {
        btnRepErrores!!.isEnabled = true
        this.btnVerGraficas!!.isEnabled = false
        this.btnRepOpAritmeticos!!.isEnabled = false
        this.btnRepGraficosDefinidos!!.isEnabled = false
    }
}