package com.example.ep1_cardoso_chavez

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditarPedidoActivity : AppCompatActivity() {
    var nombre: String = ""
    var direccion: String = ""
    var telefono: String = ""
    var detalles: String = ""
    var cantidad: Int = 0
    lateinit var pedido: Pedido

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_producto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val selectedHamburguesa = intent.getStringExtra("hamburguesa")
        val idPedido = intent.getStringExtra("idPedido")?.toInt() ?: -1
        val textHamburguesa = findViewById<TextView>(R.id.selectedHamburguesa)

        textHamburguesa.text = "Hamburguesa seleccionada: " + selectedHamburguesa

        val editNombre = findViewById<TextView>(R.id.editNombre)
        val editDireccion = findViewById<TextView>(R.id.editDireccion)
        val editTelefono = findViewById<TextView>(R.id.editTelefono)
        val editDetalles = findViewById<TextView>(R.id.editDetalles)
        val editCantidad = findViewById<TextView>(R.id.editCantidad)
        val btnPedir = findViewById<TextView>(R.id.buttonPedido)

        val context = this
        val db = DataBaseLITE(context)
        val pedido = db.getPedido(idPedido);

        editNombre.text = pedido.nombre
        editDireccion.text = pedido.direccion
        editTelefono.text = pedido.telefono
        editDetalles.text = pedido.detalles
        editCantidad.text = pedido.cantidad.toString()

        btnPedir.setOnClickListener {
            nombre = editNombre.text.toString()
            direccion = editDireccion.text.toString()
            telefono = editTelefono.text.toString()
            detalles = editDetalles.text.toString()
            cantidad = editCantidad.text.toString().toInt()

            pedido.nombre = nombre
            pedido.direccion = direccion
            pedido.telefono = telefono
            pedido.detalles = detalles
            pedido.cantidad = cantidad
            val rows = db.updatePedido(pedido)
            if (rows != 0) {
                val intent = Intent(this, PedidoActivity::class.java)
                intent.putExtra("idPedido", pedido.id.toString())
                startActivity(intent)
            }
        }
    }
}