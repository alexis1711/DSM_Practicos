package com.example.menukotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.menukotlin.R
import com.example.menukotlin.datos.Estudiante
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Opcion1Activity : AppCompatActivity() {
    private var edtNombre: EditText? = null
    private var edtNota1: EditText? = null
    private var edtNota2: EditText? = null
    private var edtNota3: EditText? = null
    private var key = ""
    private var nombre = ""
    private var nota1 = ""
    private var nota2 = ""
    private var nota3 = ""
    private var accion = ""
    private lateinit var database:DatabaseReference
    lateinit var tvopcion1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.opcion1)
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
        edtNombre = findViewById(R.id.et6)
        edtNota1 = findViewById(R.id.et1)
        edtNota2 = findViewById(R.id.et2)
        edtNota3 = findViewById(R.id.et3)

        val edtNombre = findViewById<EditText>(R.id.et6)
        val edtNota1 = findViewById<EditText>(R.id.et1)
        val edtNota2 = findViewById<EditText>(R.id.et2)
        val edtNota3 = findViewById<EditText>(R.id.et3)

        val datos: Bundle? = intent.extras
        if(datos != null) {
            key = datos.getString("key").toString()
        }
        if(datos != null){
            edtNombre.setText(intent.getStringExtra("nombre").toString())
        }
        if(datos != null){
            edtNota1.setText(intent.getStringExtra("nota1").toString())
        }
        if(datos != null){
            edtNota2.setText(intent.getStringExtra("nota2").toString())
        }
        if(datos != null){
            edtNota3.setText(intent.getStringExtra("nota3").toString())
        }
        if(datos != null) {
            accion = datos.getString("accion").toString()
        }
    }

    fun guardar(v: View?){
        val nombre: String = edtNombre?.text.toString()
        val nota1: Int = edtNota1?.text.toString().toInt()
        val nota2: Int = edtNota2?.text.toString().toInt()
        val nota3: Int = edtNota3?.text.toString().toInt()

        database = FirebaseDatabase.getInstance().getReference("estudiantes")

        val estudiante = Estudiante(nombre, nota1, nota2, nota3)
        if(accion == "a"){
            database.child(nombre).setValue(estudiante).addOnSuccessListener {
                Toast.makeText(this, "Se guardó con exito", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }else{
            val key = database.child("nombre").push().key
            if(key == null){
                Toast.makeText(this, "Llave vacia", Toast.LENGTH_SHORT).show()
            }
            val estudiantesValues = estudiante.toMap()
            val childUpdates = hashMapOf<String, Any>(
                "$nombre" to estudiantesValues
            )
            database.updateChildren(childUpdates)
            Toast.makeText(this, "Se actualizo con exito", Toast.LENGTH_SHORT).show()
        }
        finish()
    }

    fun cancelar(v: View?){
        finish()
    }

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