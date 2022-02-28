package com.example.objetos

class Ejecuciones {
    var ejecuciones: ArrayList<String> = ArrayList()

    constructor(entrada: String){
        var palabras = entrada.split("%")
        for (i in 0 until palabras.size-1){
            this.ejecuciones.add(palabras[i])
        }
    }


}