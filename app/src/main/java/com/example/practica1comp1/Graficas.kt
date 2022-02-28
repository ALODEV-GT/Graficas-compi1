package com.example.practica1comp1

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.objetos.Barras
import com.example.objetos.Grafica
import com.example.objetos.Pie
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter


class Graficas : AppCompatActivity() {
    private var layout: LinearLayout? = null

    //Colors
    private val color1: Int = Color.argb(255, 84, 71, 140)
    private val color2: Int = Color.argb(255, 185, 231, 105)
    private val color3: Int = Color.argb(255, 4, 139, 168)
    private val color4: Int = Color.argb(255, 239, 234, 90)
    private val color5: Int = Color.argb(255, 44, 105, 154)
    private val color6: Int = Color.argb(255, 242, 158, 76)
    private val color7: Int = Color.argb(255, 131, 227, 119)
    private val color8: Int = Color.argb(255, 241, 196, 83)
    private val color9: Int = Color.argb(255, 13, 179, 158)
    private val color10: Int = Color.argb(255, 22, 219, 147)
    private val color11: Int = Color.argb(255, 255, 0, 0)
    private val color12: Int = Color.argb(255, 255, 135, 0)
    private val color13: Int = Color.argb(255, 255, 211, 0)
    private val color14: Int = Color.argb(255, 222, 255, 10)
    private val color15: Int = Color.argb(255, 10, 239, 255)
    private val color16: Int = Color.argb(255, 88, 10, 255)
    private val color17: Int = Color.argb(255, 190, 10, 255)
    private val colors = intArrayOf(color1,color2,color3,color4,color5,color6,color7,color8,color9,color10,
    color11,color12,color13,color14,color15,color16,color17)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graficas)
        layout = findViewById<View>(R.id.layout) as LinearLayout
        val bundle = intent.extras

        if (bundle != null){
            var listaObjetos: ArrayList<Grafica> = bundle.getSerializable("graficas") as ArrayList<Grafica>
            var ejecuciones: ArrayList<String> = bundle.getSerializable("ejecuciones") as ArrayList<String>
            selecciorGraficas(listaObjetos,ejecuciones)
        }
    }

    fun selecciorGraficas(listaObjetos: ArrayList<Grafica>, ejecuciones: ArrayList<String>){
        for (i in 0 until ejecuciones.size){
            var tituloGrafica = ejecuciones[i]
            var grafica = buscarGrafica(listaObjetos, tituloGrafica)
            construirGraficas(grafica)
        }
    }

    fun buscarGrafica(listaObjetos: ArrayList<Grafica>, tituloGrafica: String): Grafica{
        for (i in 0 until listaObjetos.size){
            if (tituloGrafica == listaObjetos[i].titulo){
                return listaObjetos[i]
            }
        }
        return listaObjetos[0]
    }

    fun construirGraficas(grafica: Grafica ) {
            if (grafica is Barras) {
                val barchart = BarChart(this)
                val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 700)
                barchart.layoutParams = lp
                createBarChart(barchart, grafica)
                layout!!.addView(barchart)
            }else if(grafica is Pie){
                val pieChart = PieChart(this)
                val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 700)
                pieChart.layoutParams = lp
                createPieCharts(pieChart, grafica)
                layout!!.addView(pieChart)
            }
    }

    fun createBarChart(barChart: BarChart, grafica: Barras) {
        //BarChart
        var barChart: BarChart = barChart
        barChart = getSameChart(barChart, grafica.titulo!!.replace("\"",""), Color.BLACK, Color.argb(150,255, 232, 214), grafica.getDatosEjeX()) as BarChart
        barChart.setDrawGridBackground(true) //Fondo de plano para las graficas
        barChart.setData(getBarData(grafica.getDatosEjeY())) //
        barChart.invalidate()
        barChart.getLegend().setEnabled(false) //Desactivar leyenda

        //Configuracion de numeros eje x, eje y
        axisX(barChart.getXAxis(), grafica.getDatosEjeX()) //
        axisLeft(barChart.getAxisLeft())
        axisRight(barChart.getAxisRight())
    }

    //Configuracion de diseno de la grafica, titulo, color titulo, background
    private fun getSameChart(chart: Chart<*>, description: String, textColor: Int, background: Int, ejeXEtiquetas: MutableList<String> ): Chart<*>{
        chart.description.text = description
        chart.description.textColor = textColor
        chart.description.textSize = 15f
        chart.setBackgroundColor(background)
        legend(chart, ejeXEtiquetas)
        return chart
    }

    private fun getBarData(ejey: MutableList<Double>): BarData {
        val barDataSet = getDataSame(BarDataSet(getBarEntries(ejey), "")) as BarDataSet
        val barData = BarData(barDataSet)
        barData.barWidth = 0.45f // grosor de la barra
        return barData
    }

    private fun getBarEntries(ejey: MutableList<Double>): java.util.ArrayList<BarEntry> {
        val entries = java.util.ArrayList<BarEntry>()
        for (i in ejey.indices)
            entries.add(BarEntry(i.toFloat(), ejey[i].toFloat())) //Se agregan los valores a las barras
        return entries
    }

    //Personalizar el diseno de los datos en la grafica
    private fun getDataSame(dataSet: DataSet<*>): DataSet<*> {
        dataSet.setColors(*colors) //Colores de las barras/partes de la grafica
        dataSet.valueTextColor = Color.BLACK //Color de los valores
        dataSet.valueTextSize = 15f //Tamanio de los valores
        return dataSet
    }

    //Para modificar el diseno de la legenda y su texto acompaniante
    private fun legend(chart: Chart<*>, ejeXEtiquetas: MutableList<String>) {
        val legend = chart.legend
        legend.form = Legend.LegendForm.LINE
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        val entries = ArrayList<LegendEntry>()
        for (i in ejeXEtiquetas.indices) {
            val entry = LegendEntry()
            entry.formColor = colors[i]
            entry.label = ejeXEtiquetas[i]
            entries.add(entry)
        }
        legend.setCustom(entries)
    }

    //Eje horizontal o eje X
    private fun axisX(axis: XAxis, ejex: MutableList<String>) {
        axis.isGranularityEnabled = true
        axis.position = XAxis.XAxisPosition.BOTTOM
        axis.valueFormatter = IndexAxisValueFormatter(ejex)
    }

    //Eje Vertical o eje Y lado izquierdo
    private fun axisLeft(axis: YAxis) {
        axis.spaceTop = 30f
        axis.axisMinimum = 0f
        axis.granularity = 1f
    }

    //Eje Vertical o eje Y lado Derecho
    private fun axisRight(axis: YAxis) {
        axis.isEnabled = false
    }

    fun createPieCharts(pieChart: PieChart, grafica: Pie) {
        //PieChart
        var pieChart = pieChart
        pieChart = getSameChart(pieChart, grafica.titulo!!.replace("\"",""), Color.BLACK, Color.argb(150,255, 232, 214), grafica.getDatosEtiquetas()) as PieChart
        pieChart.holeRadius = 15f //Radio del circulo del centro
        pieChart.setHoleColor(Color.argb(150,255, 232, 214)) //Color del circulo del centro
        pieChart.transparentCircleRadius = 30f //Radio del circulo semi-transparente del centro
        pieChart.setTransparentCircleColor(Color.argb(150,255, 232, 214)) //Color del circulo semi-transparente del centro
        pieChart.invalidate()
        pieChart.data = getPieData(grafica.getDatosValores()) //
        pieChart.isDrawHoleEnabled = true //Activar/desactivar circulo del centro
    }

    private fun getPieData(valores: MutableList<Double>): PieData {
        val pieDataSet = getDataSame(PieDataSet(getPieEntries(valores), "")) as PieDataSet
        pieDataSet.sliceSpace = 2f // Espacio entre las partes del Pie
        pieDataSet.valueFormatter = PercentFormatter()
        return PieData(pieDataSet)
    }

    private fun getPieEntries(valores: MutableList<Double>): java.util.ArrayList<PieEntry> {
        val entries = java.util.ArrayList<PieEntry>()
        for (i in valores.indices) entries.add(PieEntry(valores[i].toFloat())) //Se agregan los valores a los pedazos del pie
        return entries
    }

}