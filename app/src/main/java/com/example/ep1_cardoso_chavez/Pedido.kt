package com.example.ep1_cardoso_chavez

import java.sql.Date

class Pedido() {
    var id = 0;
    var nombre = "";
    var direccion = "";
    var telefono = "";
    var detalles = "";
    var cantidad = 0;
    var tipo = "";
    var completado = false;
    var fecha = "";
    constructor(nombre: String, direccion: String, telefono: String, detalles: String, tipo: String?, cantidad: Int) : this() {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.detalles = detalles;
        this.tipo = tipo ?: "clasica"
        this.cantidad = cantidad;
        this.completado = false;
    }
}