package com.example.menukotlin

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.menukotlin.datos.Empleado
import com.example.menukotlin.datos.Estudiante

class AdaptadorEmpleado(private val context: Activity, var empleados: List<Empleado>):
    ArrayAdapter<Empleado?>(context, R.layout.empleado_layout, empleados){
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val layoutInflater = context.layoutInflater
        var rowview: View? = null
        rowview = view ?:layoutInflater.inflate(R.layout.empleado_layout, null)
        val tvNombre = rowview!!.findViewById<TextView>(R.id.tvNombre)
        val tvSalarioB = rowview!!.findViewById<TextView>(R.id.tvSalarioB)
        tvNombre.text = "Nombre: " + empleados[position].nombre
        tvSalarioB.text = "SalarioB: " + empleados[position].salarioB.toString()
        return rowview
    }
}