package com.example.practica1comp1

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class RepGraficasDefinidas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rep_graficas_definidas)
        this.setTitle(R.string.tituloRepNumGraficas)

        val bundle = intent.extras

        if(bundle != null){
            var numGraficasBarras = bundle.get("numGraficasBarras") as Int
            var numGraficasPie = bundle.get("numGraficasPie") as Int
            val tabla: TableLayout = findViewById(R.id.tablaNumGraficas)

            val barras = TextView(this)
            val numBarras= TextView(this)
            numBarras.gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL
            val pie= TextView(this)
            val numPie= TextView(this)
            numPie.gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL
            var row1 = TableRow(this)
            var row2 = TableRow(this)
            barras.setPadding(30,20,30,20)
            pie.setPadding(30,20,30,20)
            numBarras.setPadding(30,20,30,20)
            numPie.setPadding(30,20,30,20)

            barras.text = "Barras"
            numBarras.text = numGraficasBarras.toString()
            row1.addView(barras)
            row1.addView(numBarras)
            tabla!!.addView(row1)

            pie.text = "Pie"
            numPie.text = numGraficasPie.toString()
            row2.addView(pie)
            row2.addView(numPie)
            tabla.addView(row2)
        }
    }
}