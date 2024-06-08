package com.example.ep1_cardoso_chavez

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var hamburguesa  = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonClasica = findViewById<Button>(R.id.clasica_button)
        val buttonDoble = findViewById<Button>(R.id.doble_button)
        val buttonTriple = findViewById<Button>(R.id.triple_button)
        val buttonHawaiana = findViewById<Button>(R.id.hawaiana_button)
        val buttonRanchera = findViewById<Button>(R.id.ranchera_button)
        val btnPedidos = findViewById<Button>(R.id.btnPedidos)

        buttonClasica.setOnClickListener {
            hamburguesa = "Clasica"
            changeActivity()
        }
        buttonDoble.setOnClickListener {
            hamburguesa = "Doble"
            changeActivity()
        }
        buttonTriple.setOnClickListener {
            hamburguesa = "Triple"
            changeActivity()
        }
        buttonHawaiana.setOnClickListener {
            hamburguesa = "Hawaiana"
            changeActivity()
        }
        buttonRanchera.setOnClickListener {
            hamburguesa = "Ranchera"
            changeActivity()
        }
        btnPedidos.setOnClickListener {
            val intent = Intent(this, VerPedidosActivity::class.java)
            startActivity(intent)
        }
    }

    fun changeActivity(){
        val intent = Intent(this, OrdenarActivity::class.java)
        intent.putExtra("hamburguesa", hamburguesa)
        startActivity(intent)
    }
}

