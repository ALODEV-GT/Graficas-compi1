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
import com.example.objetos.Grafica
import java.io.StringReader

class MainActivity : AppCompatActivity() {
    private var btnVerGraficas: Button? = null
    private var btnRepOpAritmeticos: Button? = null
    private var btnRepGraficosDefinidos: Button? = null
    private var btnRepErrores: Button? = null

    private var graficasObj: MutableMap<String, Grafica>? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        startActivity(intent)
    }

    fun reporteGraficasDefinidas(view: View) {
        val intent = Intent(this, RepGraficasDefinidas::class.java)
        startActivity(intent)
    }

    fun reporteErrores(view: View) {
        val intent = Intent(this, RepErrores::class.java)
        startActivity(intent)
    }

    fun verGraficas(view: View) {
        val intent = Intent(this, Graficas::class.java)
        var bundle: Bundle = Bundle()
        for (grafica in graficasObj!!){
            bundle.putSerializable(grafica.key, grafica.value)
        }
        val oe: String

        startActivity(intent)
    }

    fun analizar(view: View) {
        val entrada = findViewById<EditText>(R.id.etEntrada)
        val sr = StringReader(entrada.text.toString())
        val analizadorLexico = AnalizadorLexico(sr)
        val analizadorSintactico = parser(analizadorLexico)

        try {
            analizadorSintactico.parse()
            Toast.makeText(applicationContext, "Analisis completado correctamente", Toast.LENGTH_SHORT).show()
            val cargarDatos = CargaDatos(analizadorSintactico.definiciones)
            println(cargarDatos.graficasObjetos())
            this.graficasObj = cargarDatos.getGraficas()
            desactivarActivarBotonesAnalisisCorrecto()
        } catch (e: MisExcepciones) {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            desactivarActivarBotonesAnalisisIncorrecto()
        } catch (e: java.lang.Exception) {
            Toast.makeText(applicationContext, "Error sintactico", Toast.LENGTH_SHORT).show()
            desactivarActivarBotonesAnalisisIncorrecto()
        }
    }

    private fun desactivarActivarBotonesAnalisisCorrecto(){
        btnRepErrores!!.isEnabled = false
        this.btnVerGraficas!!.isEnabled = true
        this.btnRepOpAritmeticos!!.isEnabled = true
        this.btnRepGraficosDefinidos!!.isEnabled = true
    }

    private fun desactivarActivarBotonesAnalisisIncorrecto(){
        btnRepErrores!!.isEnabled = true
        this.btnVerGraficas!!.isEnabled = false
        this.btnRepOpAritmeticos!!.isEnabled = false
        this.btnRepGraficosDefinidos!!.isEnabled = false
    }
}