package com.example.menukotlin

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.menukotlin.datos.Empleado
import com.example.menukotlin.datos.Estudiante
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class EmpleadosActivity : AppCompatActivity() {
    var consultaOrdenada: Query = refEmpleados.orderByChild("nombre")
    var empleados: MutableList<Empleado>? =  null
    var listaEmpleados: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empleados)
        inicializar()
    }

    private fun inicializar(){
        val fab_agregar: FloatingActionButton = findViewById(R.id.fab_agregar)
        listaEmpleados = findViewById(R.id.ListaEmpleados)

        listaEmpleados!!.setOnItemClickListener(object : AdapterView.OnItemClickListener{
            override fun onItemClick(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                val intent = Intent(getBaseContext(), Opcion2Activity::class.java)
                intent.putExtra("accion", "e")
                intent.putExtra("key", empleados!![i].key)
                intent.putExtra("nombre", empleados!![i].nombre)
                intent.putExtra("salarioB", empleados!![i].salarioB)
                startActivity(intent)
            }
        })

        listaEmpleados!!.onItemLongClickListener = object : AdapterView.OnItemLongClickListener{
            override fun onItemLongClick(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                l: Long
            ): Boolean {
                val ad = AlertDialog.Builder(this@EmpleadosActivity)
                ad.setMessage("Está seguro de eliminar registro?")
                    .setTitle("Confirmación")
                ad.setPositiveButton("Si"){
                        dialog, id -> empleados!![position].nombre?.let{
                    refEmpleados.child(it).removeValue()
                }
                    Toast.makeText(
                        this@EmpleadosActivity,
                        "Registro borrado!", Toast.LENGTH_SHORT
                    ).show()
                }
                ad.setNegativeButton("No", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface, id: Int){
                        Toast.makeText(
                            this@EmpleadosActivity,
                            "Operación de borrado cancelada!", Toast.LENGTH_SHORT
                        ).show()
                    }
                })
                ad.show()
                return true
            }
        }

        fab_agregar.setOnClickListener(View.OnClickListener {
            val i = Intent(getBaseContext(), Opcion2Activity::class.java)
            i.putExtra("accion", "a")
            i.putExtra("key","")
            i.putExtra("nombre", "")
            i.putExtra("salarioB","")
            startActivity(i)
        })
        empleados = ArrayList<Empleado>()

        consultaOrdenada.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                empleados!!.removeAll(empleados!!)
                for(dato in dataSnapshot.children){
                    val empleado: Empleado? = dato.getValue(Empleado::class.java)
                    empleado?.key(dato.key)
                    if(empleado != null){
                        empleados!!.add((empleado))
                    }
                }
                val adapter = AdaptadorEmpleado(
                    this@EmpleadosActivity,
                    empleados as ArrayList<Empleado>
                )
                listaEmpleados!!.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }

    companion object{
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var refEmpleados: DatabaseReference = database.getReference("empleados")
    }
}