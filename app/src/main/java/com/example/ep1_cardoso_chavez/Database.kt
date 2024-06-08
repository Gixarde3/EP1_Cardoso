package com.example.ep1_cardoso_chavez

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


const val DATABASE_NAME ="MyDB"
const val TABLE_NAME="Pedidos"
const val COL_NOMBRE = "nombre"
const val COL_TELEFONO = "telefono"
const val COL_DIRECCION = "direccion"
const val COL_DETALLES = "detalles"
const val COL_CANTIDAD = "cantidad"
const val COL_COMPLETADO = "completado"
const val COL_FECHA = "fecha"
const val COL_TIPO = "tipo"
const val COL_ID = "id"

class DataBaseLITE(var context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,3) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE $TABLE_NAME " +
                "($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_NOMBRE VARCHAR(255)," +
                "$COL_TELEFONO VARCHAR(255)," +
                "$COL_DIRECCION VARCHAR(255)," +
                "$COL_DETALLES VARCHAR(256)," +
                "$COL_TIPO VARCHAR(255)," +
                "$COL_CANTIDAD INTEGER," +
                "$COL_FECHA DATE DEFAULT CURRENT_TIMESTAMP," +
                "$COL_COMPLETADO BOOLEAN)"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun insertPedido(pedido: Pedido): kotlin.Long {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NOMBRE, pedido.nombre)
        cv.put(COL_TELEFONO, pedido.telefono)
        cv.put(COL_DIRECCION, pedido.direccion)
        cv.put(COL_DETALLES, pedido.detalles)
        cv.put(COL_TIPO, pedido.tipo)
        cv.put(COL_CANTIDAD, pedido.cantidad)

        cv.put(COL_COMPLETADO, false)
        val result = db.insert(TABLE_NAME, null, cv)
        if (result != (-1).toLong())
            Toast.makeText(context, "Insertado Correctamente con el id $result", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Fallo", Toast.LENGTH_SHORT).show()

        return result
    }


    fun getPedidos() : MutableList<Pedido>{
        val list : MutableList<Pedido> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()) do {
            var pedido = Pedido()
            pedido.id = result.getString(result.getColumnIndexOrThrow(COL_ID)).toInt()
            pedido.nombre = result.getString(result.getColumnIndexOrThrow(COL_NOMBRE))
            pedido.telefono = result.getString(result.getColumnIndexOrThrow(COL_TELEFONO))
            pedido.direccion = result.getString(result.getColumnIndexOrThrow(COL_DIRECCION))
            pedido.detalles = result.getString(result.getColumnIndexOrThrow(COL_DETALLES))
            pedido.tipo = result.getString(result.getColumnIndexOrThrow(COL_TIPO))
            pedido.fecha = result.getString(result.getColumnIndexOrThrow(COL_FECHA))
            pedido.cantidad = result.getString(result.getColumnIndexOrThrow(COL_CANTIDAD)).toInt()
            pedido.completado = result.getString(result.getColumnIndexOrThrow(COL_COMPLETADO)).toBoolean()
            list.add(pedido)
        }while (result.moveToNext())
        result.close()

        return list
    }

    fun getPedido(id: Int) : Pedido{
        var pedido = Pedido()
        val db = this.writableDatabase
        val query = "Select * from $TABLE_NAME where $COL_ID = $id"
        val result = db.rawQuery(query,null)
        result.moveToFirst()
        pedido.id = result.getString(result.getColumnIndexOrThrow(COL_ID)).toInt()
        pedido.nombre = result.getString(result.getColumnIndexOrThrow(COL_NOMBRE))
        pedido.telefono = result.getString(result.getColumnIndexOrThrow(COL_TELEFONO))
        pedido.direccion = result.getString(result.getColumnIndexOrThrow(COL_DIRECCION))
        pedido.detalles = result.getString(result.getColumnIndexOrThrow(COL_DETALLES))
        pedido.tipo = result.getString(result.getColumnIndexOrThrow(COL_TIPO))
        pedido.fecha = result.getString(result.getColumnIndexOrThrow(COL_FECHA))
        pedido.cantidad = result.getString(result.getColumnIndexOrThrow(COL_CANTIDAD)).toInt()
        pedido.completado = result.getString(result.getColumnIndexOrThrow(COL_COMPLETADO)).toBoolean()
        result.close()

        return pedido
    }

    fun updatePedido(pedido: Pedido): Int {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NOMBRE, pedido.nombre)
        cv.put(COL_TELEFONO, pedido.telefono)
        cv.put(COL_DIRECCION, pedido.direccion)
        cv.put(COL_DETALLES, pedido.detalles)
        cv.put(COL_TIPO, pedido.tipo)
        cv.put(COL_CANTIDAD, pedido.cantidad)
        cv.put(COL_COMPLETADO, pedido.completado)
        val result = db.update(TABLE_NAME, cv, "$COL_ID = ?", arrayOf(pedido.id.toString()))
        return result
    }

    fun deletePedido(id: Int): Int {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, "$COL_ID = ?", arrayOf(id.toString()))

        return result
    }

    fun completarPedido(id: Int): Int {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_COMPLETADO, true)
        val result = db.update(TABLE_NAME, cv, "$COL_ID = ?", arrayOf(id.toString()))

        return result
    }
}