
package com.example.ep1_cardoso_chavez

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrdenarActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    var nombre = "";
    var direccion = "";
    var telefono = "";
    var detalles = "";
    var cantidad = 0;
    var pedido = Pedido();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ordenar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val selectedHamburguesa = intent.getStringExtra("hamburguesa")
        val textHamburguesa = findViewById<TextView>(R.id.selectedHamburguesa)

        textHamburguesa.text = "Hamburguesa seleccionada: " + selectedHamburguesa

        val editNombre = findViewById<TextView>(R.id.editNombre)
        val editDireccion = findViewById<TextView>(R.id.editDireccion)
        val editTelefono = findViewById<TextView>(R.id.editTelefono)
        val editDetalles = findViewById<TextView>(R.id.editDetalles)
        val editCantidad = findViewById<TextView>(R.id.editCantidad)
        val btnPedir = findViewById<TextView>(R.id.buttonPedido)
        val context = this
        var db = DataBaseLITE(context)

        btnPedir.setOnClickListener {
            nombre = editNombre.text.toString()
            direccion = editDireccion.text.toString()
            telefono = editTelefono.text.toString()
            detalles = editDetalles.text.toString()
            cantidad = editCantidad.text.toString().toInt()
            pedido = Pedido(nombre, direccion, telefono, detalles, selectedHamburguesa, cantidad)
            val idPedido = db.insertPedido(pedido)

            if (idPedido != -1L) {
                val intent = Intent(this, PedidoActivity::class.java)
                intent.putExtra("idPedido", idPedido.toString())
                startActivity(intent)
            }
        }
    }
}