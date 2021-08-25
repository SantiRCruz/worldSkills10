package com.example.worldskills10.repository.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.worldskills10.models.Constants
import com.example.worldskills10.models.local.DBPedido
import com.example.worldskills10.models.webService.historial.JsonPedido

class DBManager(context: Context) {

    val dbHelper = DBHelper(context)
    var db : SQLiteDatabase ?= null

    fun openDbWr(){
        db = dbHelper.writableDatabase
    }
    fun openDbRd(){
        db = dbHelper.readableDatabase
    }

    fun closeDb(){
       if (db!=null){
           db?.close()
       }
    }

    fun insertData(dbPedido: DBPedido):Long{
        openDbWr()
        val values = ContentValues()
        values.put(Constants.TABLE_COLUMN_2,dbPedido.idProducto)
        values.put(Constants.TABLE_COLUMN_3,dbPedido.nombre)
        values.put(Constants.TABLE_COLUMN_4,dbPedido.descripcion)
        values.put(Constants.TABLE_COLUMN_5,dbPedido.url_imagen)
        values.put(Constants.TABLE_COLUMN_6,dbPedido.precioUnidad)
        values.put(Constants.TABLE_COLUMN_7,dbPedido.precioTotal)
        values.put(Constants.TABLE_COLUMN_8,dbPedido.cantidad)
        val res = db?.insert(Constants.TABLE_NAME,null,values)
        closeDb()
        return res!!
    }
    fun updateData(id:Int,precioTotal:Int,cantidad:Int):Int{
        openDbWr()
        val values = ContentValues()
        values.put(Constants.TABLE_COLUMN_7,precioTotal)
        values.put(Constants.TABLE_COLUMN_8,cantidad)
        val res = db?.update(Constants.TABLE_NAME,values, " id=? " , arrayOf(id.toString()))
        closeDb()
        return res!!
    }

    fun sumTotal():Int{
        var valor =0
        openDbRd()
        val result = db?.rawQuery(" SELECT SUM ( " + Constants.TABLE_COLUMN_7 + " ) FROM " + Constants.TABLE_NAME  ,null)
        if (result!!.moveToFirst())
                valor = result.getInt(0)
        closeDb()
        return valor
    }

    fun listAcumulacion(id:Int):MutableList<DBPedido>{
        val lista : MutableList<DBPedido> = mutableListOf()
        openDbRd()
        val result = db?.rawQuery(Constants.QUERY_ALL + " WHERE " + Constants.TABLE_COLUMN_2 + " =? ",
            arrayOf(id.toString()))
        if (result!!.moveToFirst())
            do {
                val dbPedido = DBPedido()
                dbPedido.id = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_1))
                dbPedido.idProducto = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_2))
                dbPedido.nombre = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_3))
                dbPedido.descripcion = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_4))
                dbPedido.url_imagen = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_5))
                dbPedido.precioUnidad = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_6))
                dbPedido.precioTotal = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_7))
                dbPedido.cantidad = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_8))
                lista.add(dbPedido)
            }while (result.moveToNext())
        closeDb()
        return lista
    }

    fun listData():MutableList<DBPedido>{
        val lista : MutableList<DBPedido> = mutableListOf()
        openDbRd()
        val result = db?.rawQuery(Constants.QUERY_ALL,null)
        if (result!!.moveToFirst())
            do {
                val dbPedido = DBPedido()
                dbPedido.id = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_1))
                dbPedido.idProducto = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_2))
                dbPedido.nombre = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_3))
                dbPedido.descripcion = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_4))
                dbPedido.url_imagen = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_5))
                dbPedido.precioUnidad = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_6))
                dbPedido.precioTotal = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_7))
                dbPedido.cantidad = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_8))
                lista.add(dbPedido)
            }while (result.moveToNext())
        closeDb()
        return lista
    }

    fun listPedido():MutableList<JsonPedido>{
        val lista : MutableList<JsonPedido> = mutableListOf()
        openDbRd()
        val result = db?.rawQuery(Constants.QUERY_ALL,null)
        if (result!!.moveToFirst())
            do {
                val jsonPedido = JsonPedido()
                jsonPedido.id_producto = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_2))
                jsonPedido.cantidad = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_8))
                jsonPedido.precio = result.getInt(result.getColumnIndex(Constants.TABLE_COLUMN_6))
                lista.add(jsonPedido)
            }while (result.moveToNext())
        closeDb()
        return lista
    }

    fun deleteId(id:Int):Int{
        openDbWr()
        val res = db?.delete(Constants.TABLE_NAME," id =? ", arrayOf(id.toString()))
        closeDb()
        return res!!
    }

    fun deleteAll():Int{
        openDbWr()
        val res = db?.delete(Constants.TABLE_NAME,null,null)
        closeDb()
        return res!!
    }

}