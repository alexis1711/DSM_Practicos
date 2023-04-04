package com.example.menukotlin.datos

class Empleado {
    fun key(key: String?){

    }

    var nombre: String? = null
    var salarioB: Double? = null
    var key: String? = null
    var est: MutableMap<String, Boolean> = HashMap()

    constructor(){}
    constructor(nombre: String?, salarioB: Double?){
        this.nombre = nombre
        this.salarioB = salarioB
    }

    fun toMap():Map<String, Any?>{
        return mapOf(
            "nombre" to nombre,
            "salarioB" to salarioB,
            "key" to key,
            "est" to est
        )
    }
}