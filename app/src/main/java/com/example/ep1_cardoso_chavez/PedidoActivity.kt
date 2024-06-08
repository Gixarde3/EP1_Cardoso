package com.example.ep1_cardoso_chavez

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PedidoActivity : AppCompatActivity() {
    var idPedido = -1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pedido)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        idPedido = intent.getStringExtra("idPedido")?.toInt() ?: -2
        val db = DataBaseLITE(this)
        val pedido = db.getPedido(idPedido)

        val textHamburguesa = findViewById<TextView>(R.id.textHamburguesa)
        val textNombre = findViewById<TextView>(R.id.textNombre)
        val textDireccion = findViewById<TextView>(R.id.textDireccion)
        val textTelefono = findViewById<TextView>(R.id.textTelefono)
        val textDetalles = findViewById<TextView>(R.id.textDetalles)
        val textCantidad = findViewById<TextView>(R.id.textCantidad)
        val btnEliminar = findViewById<Button>(R.id.btnEliminar)
        val btnEditar = findViewById<Button>(R.id.btnEditar)


        textHamburguesa.text = pedido.tipo
        textNombre.text = pedido.nombre
        textDireccion.text = pedido.direccion
        textTelefono.text = pedido.telefono
        textDetalles.text = pedido.detalles
        textCantidad.text = pedido.cantidad.toString() + " hamburguesas " + pedido.tipo + "s"

        btnEliminar.setOnClickListener{
            db.deletePedido(idPedido)
            Toast.makeText(this, "Pedido eliminado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, VerPedidosActivity::class.java)
            startActivity(intent)
        }

        btnEditar.setOnClickListener{
            val intent = Intent(this, EditarPedidoActivity::class.java)
            intent.putExtra("idPedido", idPedido.toString())
            intent.putExtra("hamburguesa", pedido.tipo)
            startActivity(intent)
        }
    }
}