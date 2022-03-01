package com.example.practica1comp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.objetos.Error

class RepErrores : AppCompatActivity() {
    private var erroresLexicos: ArrayList<Error>? = null
    private var erroresSintacticos: ArrayList<Error>? = null
    private var tabla: TableLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rep_errores)
        this.setTitle(R.string.tituloRepErrores)

        val bundle = intent.extras

        if (bundle != null) {
            erroresLexicos = bundle.get("erroresLexicos") as ArrayList<Error>
            erroresSintacticos = bundle.get("erroresSintacticos") as ArrayList<Error>
            tabla = findViewById(R.id.tablaOperadores)
            agregarErrores(this.erroresLexicos!!)
            agregarErrores(this.erroresSintacticos!!)

        }
    }

    private fun agregarErrores(errores: ArrayList<Error>) {
        errores.forEach {
            var lexema = TextView(this)
            var linea = TextView(this)
            var columna = TextView(this)
            var tipo = TextView(this)
            var descripcion = TextView(this)
            var row = TableRow(this)
            lexema.setPadding(30, 20, 30, 20)
            linea.setPadding(30, 20, 30, 20)
            columna.setPadding(30, 20, 30, 20)
            tipo.setPadding(30, 20, 30, 20)
            descripcion.setPadding(30, 20, 30, 20)

            lexema.text = it.lexema
            linea.text = it.linea.toString()
            columna.text = it.columna.toString()
            tipo.text = it.tipo
            descripcion.text = it.descripcion

            row.addView(lexema)
            row.addView(linea)
            row.addView(columna)
            row.addView(tipo)
            row.addView(descripcion)
            tabla!!.addView(row)
        }
    }

}