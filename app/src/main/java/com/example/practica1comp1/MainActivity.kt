package com.example.practica1comp1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cargarDatos.CargaDatos
import com.example.excepciones.MisExcepciones
import com.example.jcup.parser
import com.example.jlex.AnalizadorLexico
import java.io.StringReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    fun reporteOperadoresAritmeticos(view: View){
        val intent = Intent(this, RepOperadoresAritmeticos::class.java)
        startActivity(intent)
    }

    fun reporteGraficasDefinidas(view: View){
        val intent = Intent(this, RepGraficasDefinidas::class.java)
        startActivity(intent)
    }

    fun reporteErrores(view: View){
        val intent = Intent(this, RepErrores::class.java)
        startActivity(intent)
    }

    fun verGraficas(view: View){
        val intent = Intent(this, Graficas::class.java)
        startActivity(intent)
    }

    fun analizar(view: View){
        val entrada = findViewById<EditText>(R.id.etEntrada)
        val sr = StringReader(entrada.text.toString())
        val lex = AnalizadorLexico(sr)
        val par = parser(lex)

        try {
            par.parse()
            val cd = CargaDatos(par.definiciones)
            Toast.makeText(applicationContext, "Analisis completado correctamente", Toast.LENGTH_SHORT).show()
        }catch (e: MisExcepciones){
            Toast.makeText(applicationContext, e.message , Toast.LENGTH_SHORT).show()
        }catch (e : java.lang.Exception){
            Toast.makeText(applicationContext, "Error sintactico" , Toast.LENGTH_SHORT).show()
        }
    }
}