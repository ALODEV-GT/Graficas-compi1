package com.example.practica1comp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.objetos.Operacion

class RepOperadoresAritmeticos : AppCompatActivity() {

    private var operaciones:ArrayList<Operacion>?=null
    private var tabla: TableLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rep_operadores_aritmeticos)
        this.setTitle(R.string.tituloReporteOp)

        val bundle = intent.extras

        if (bundle != null){
            operaciones = bundle.get("operaciones") as ArrayList<Operacion>
            tabla= findViewById(R.id.tablaOperadores)

            operaciones!!.forEach {
                var operador = TextView(this)
                var linea=TextView(this)
                var columna=TextView(this)
                var ocurrencia=TextView(this)
                var row= TableRow(this)
                operador.setPadding(30,20,30,20)
                linea.setPadding(30,20,30,20)
                columna.setPadding(30,20,30,20)
                ocurrencia.setPadding(30,20,30,20)

                operador.text = it.operador
                linea.text = it.linea.toString()
                columna.text = it.columna.toString()
                ocurrencia.text = it.ocurrencia

                row.addView(operador)
                row.addView(linea)
                row.addView(columna)
                row.addView(ocurrencia)
                tabla!!.addView(row)
            }

        }
    }
}