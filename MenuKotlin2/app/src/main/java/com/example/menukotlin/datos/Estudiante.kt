package com.example.menukotlin.datos

class Estudiante {
    fun key(key: String?){

    }

    var nombre: String? = null
    var nota1: Int? = null
    var nota2: Int? = null
    var nota3: Int? = null
    var key: String? = null
    var est: MutableMap<String, Boolean> = HashMap()

    constructor(){}
    constructor(nombre: String?, nota1: Int?, nota2: Int?, nota3: Int?){
        this.nombre = nombre
        this.nota1 = nota1
        this.nota2 = nota2
        this.nota3 = nota3
    }

    fun toMap():Map<String, Any?>{
        return mapOf(
            "nombre" to nombre,
            "nota1" to nota1,
            "nota2" to nota2,
            "nota3" to nota3,
            "key" to key,
            "est" to est
        )
    }
}