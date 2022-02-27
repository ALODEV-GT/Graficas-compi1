package com.example.objetos

import java.io.Serializable

class Seri: Serializable {

    var name = ""
    var id = 0
    var place = ""

    constructor(mName: String, mId: Int, mPlace: String){
        name = mName
        id = mId
        place = mPlace
    }

    constructor()
}