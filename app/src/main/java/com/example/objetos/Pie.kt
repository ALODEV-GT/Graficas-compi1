package com.example.objetos

class Pie(
            val TIPO: String = "PIE",
            titulo: String? = null,
            var etiquetas: List<String>?=null,
            var valores: List<String>?=null,
            var tipo: String?=null,
            var total: Int?=null,
            var extra: String?=null,
            uniones: List<String>? = null
         ): Grafica(titulo,uniones) {

    override fun toString(): String {
        return "OBJETO:$TIPO ---> titulo: ${super.titulo} etiquetas:$etiquetas valores:$valores tipo:$tipo total:$total extra:$extra uniones:${super.uniones}"
    }
}