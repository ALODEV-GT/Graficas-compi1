package com.example.practica1comp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

    fun message(view: View){
        val entrada = findViewById<EditText>(R.id.etEntrada)
        val salida = findViewById<TextView>(R.id.tvLog)

        val sr = StringReader(entrada.text.toString())
        val lex = AnalizadorLexico(sr)
        val par = parser(lex)

        try {
            par.parse()
            val cd = CargaDatos(par.definiciones)
            salida.text = cd.graficasObjetos()
            println(cd.graficasObjetos())
            Toast.makeText(applicationContext, "Analisis completado correctamente", Toast.LENGTH_SHORT).show()
        }catch (e: MisExcepciones){
            Toast.makeText(applicationContext, e.message , Toast.LENGTH_SHORT).show()
        }catch (e : java.lang.Exception){
            salida.text = "Error sintactico"
        }
    }
}