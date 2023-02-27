package com.example.menukotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.menukotlin.R

class Opcion1Activity : AppCompatActivity() {
    lateinit var tvopcion1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuopciones, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.opcion1) {
            Toast.makeText(this, "Se seleccionó la primer opción", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Opcion1Activity::class.java)
            startActivity(intent)
        }
        if (id == R.id.opcion2) {
            Toast.makeText(this, "Se seleccionó la segunda opción", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Opcion2Activity::class.java)
            startActivity(intent)
        }
        if (id == R.id.opcion3) {
            Toast.makeText(this, "Se seleccionó la tercer opción", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Opcion3Activity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}