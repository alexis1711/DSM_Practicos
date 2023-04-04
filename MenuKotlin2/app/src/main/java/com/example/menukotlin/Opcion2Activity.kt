package com.example.menukotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.menukotlin.R
import com.example.menukotlin.datos.Empleado
import com.example.menukotlin.datos.Estudiante
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Opcion2Activity : AppCompatActivity() {

    private var edtNombre: EditText? = null
    private var edtSalarioB: EditText? = null
    private var key = ""
    private var nombre = ""
    private var salarioB = ""
    private var accion = ""
    private lateinit var database: DatabaseReference
    lateinit var tvopcion1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.opcion2)
        inicializar()
        /*super.onCreate(savedInstanceState)
        setContentView(R.layout.opcion1)

        val tv1=findViewById<TextView>(R.id.tv1)
        val button=findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val et1=findViewById<EditText>(R.id.et1)
            val et2=findViewById<EditText>(R.id.et)
            val et3=findViewById<EditText>(R.id.et4)
            val et4=findViewById<EditText>(R.id.et3)
            val et5=findViewById<EditText>(R.id.et5)
            val nro1 = et1.text.toString().toInt()
            val nro2 = et2.text.toString().toInt()
            val nro3 = et3.text.toString().toInt()
            val nro4 = et4.text.toString().toInt()
            val nro5 = et5.text.toString().toInt()
            val suma = (nro1 + nro2 + nro3 + nro4 +nro5)/5
            if(suma >= 6)
                tv1.text = "Aprobado con: ${suma.toString()} "
            else
                tv1.text = "Reprobado con: ${suma.toString()} "
        }*/

    }

    private fun inicializar(){
        edtNombre = findViewById(R.id.et2)
        edtSalarioB = findViewById(R.id.et1)

        val edtNombre = findViewById<EditText>(R.id.et2)
        val edtSalarioB = findViewById<EditText>(R.id.et1)

        val datos: Bundle? = intent.extras
        if(datos != null) {
            key = datos.getString("key").toString()
        }
        if(datos != null){
            edtNombre.setText(intent.getStringExtra("nombre").toString())
        }
        if(datos != null){
            edtSalarioB.setText(intent.getStringExtra("salarioB").toString())
        }
        if(datos != null) {
            accion = datos.getString("accion").toString()
        }
    }

    fun guardar(v: View?){
        val nombre: String = edtNombre?.text.toString()
        val salarioB: Double = edtSalarioB?.text.toString().toDouble()

        database = FirebaseDatabase.getInstance().getReference("empleados")

        val empleado = Empleado(nombre, salarioB)
        if(accion == "a"){
            database.child(nombre).setValue(empleado).addOnSuccessListener {
                Toast.makeText(this, "Se guardó con exito", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }else{
            val key = database.child("nombre").push().key
            if(key == null){
                Toast.makeText(this, "Llave vacia", Toast.LENGTH_SHORT).show()
            }
            val empleadosValues = empleado.toMap()
            val childUpdates = hashMapOf<String, Any>(
                "$nombre" to empleadosValues
            )
            database.updateChildren(childUpdates)
            Toast.makeText(this, "Se actualizo con exito", Toast.LENGTH_SHORT).show()
        }
        finish()
    }

    fun cancelar(v: View?){
        finish()
    }
      /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.opcion2)

        val et1 = findViewById<EditText>(R.id.et1)
        val tv1=findViewById<TextView>(R.id.tv1)
        val button=findViewById<Button>(R.id.button)

        button.setOnClickListener {

            val nro1 = et1.text.toString().toInt()
            val salario = nro1 - (nro1 * 0.03) - (nro1 * 0.04) - (nro1 * 0.05)
            tv1.text = "Su salario neto es: ${salario.toString()}"
        }

    }*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuopciones, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.opcion1) {
            Toast.makeText(this, "Se seleccionó la primer opción", Toast.LENGTH_LONG).show()
            val intent = Intent(this, EstudiantesActivity::class.java)
            startActivity(intent)
        }
        if (id == R.id.opcion2) {
            Toast.makeText(this, "Se seleccionó la segunda opción", Toast.LENGTH_LONG).show()
            val intent = Intent(this, EmpleadosActivity::class.java)
            startActivity(intent)
        }
        if (id == R.id.opcion3) {
            Toast.makeText(this, "Se seleccionó la tercer opción", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Opcion3Activity::class.java)
            startActivity(intent)
        }
        if(id == R.id.cerrar){
            FirebaseAuth.getInstance().signOut().also {
                Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}