package com.example.menukotlin

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.menukotlin.datos.Estudiante

class AdaptadorEstudiante(private val context: Activity, var estudiantes: List<Estudiante>):
    ArrayAdapter<Estudiante?>(context, R.layout.estudiante_layout, estudiantes){
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val layoutInflater = context.layoutInflater
        var rowview: View? = null
        rowview = view ?:layoutInflater.inflate(R.layout.estudiante_layout, null)
        val tvNombre = rowview!!.findViewById<TextView>(R.id.tvNombre)
        val tvNota1 = rowview!!.findViewById<TextView>(R.id.tvNota1)
        val tvNota2 = rowview!!.findViewById<TextView>(R.id.tvNota2)
        val tvNota3 = rowview!!.findViewById<TextView>(R.id.tvNota3)
        tvNombre.text = "Nombre: " + estudiantes[position].nombre
        tvNota1.text = "Nota1: " + estudiantes[position].nota1.toString()
        tvNota2.text = "Nota2: " + estudiantes[position].nota2.toString()
        tvNota3.text = "Nota3: " + estudiantes[position].nota3.toString()
        return rowview
    }
}