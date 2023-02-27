package com.example.menukotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.menukotlin.R


class Opcion2Activity : AppCompatActivity() {
       override fun onCreate(savedInstanceState: Bundle?) {
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