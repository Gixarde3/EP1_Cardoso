package com.example.ep1_cardoso_chavez

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListaPedidosAdapter(val pedidos: List<Pedido>) : RecyclerView.Adapter<ListaPedidosAdapter.PedidoViewHolder>() {

    class PedidoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pedidoHamburguesa: TextView = itemView.findViewById(R.id.txtHamburguesa)
        val pedidoCantidad: TextView = itemView.findViewById(R.id.txtCantidad)
        val pedidoDetalles: TextView = itemView.findViewById(R.id.txtDetalles)
        val pedidoFecha: TextView = itemView.findViewById(R.id.txtFecha)
        var idPedido: Int = 0

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, PedidoActivity::class.java)
                intent.putExtra("idPedido", idPedido.toString())
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_item_pedido, null, false)
        return PedidoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = pedidos.get(position)
        holder.pedidoHamburguesa.text = pedido.tipo
        holder.pedidoCantidad.text = pedido.cantidad.toString()
        holder.pedidoDetalles.text = pedido.detalles
        holder.pedidoFecha.text = pedido.fecha
        holder.idPedido = pedido.id
    }

    override fun getItemCount(): Int {
        return pedidos.size
    }
}