package com.example.worldskills10.models

class Constants {
    companion object{

        //sharedpreferences
            val KEY_CORREO = "correo"
            val KEY_CONTRASENA = "contrasena"
            val KEY_RECORDAR = "recordar"
            val KEY_ID_USER = "idUser"
            val KEY_TOKEN = "token"
            val KEY_USER_NAME = "userName"



        var ID_CATEGORIA = 0
        var ID_PRODUCTO = 0

        //bd
        val BD_NAME = "pedidos"
        val BD_VERSION = 1

        val TABLE_NAME  = "producto"
        val TABLE_COLUMN_1  = "id"
        val TABLE_COLUMN_2  = "idProducto"
        val TABLE_COLUMN_3  = "nombre"
        val TABLE_COLUMN_4  = "descripcion"
        val TABLE_COLUMN_5  = "url_imagen"
        val TABLE_COLUMN_6  = "precioUnidad"
        val TABLE_COLUMN_7  = "precioTotal"
        val TABLE_COLUMN_8  = "cantidad"

        val TABLE_CREATE = " CREATE TABLE " + TABLE_NAME + " ( " +
                TABLE_COLUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                TABLE_COLUMN_2 + " INTEGER NOT NULL , " +
                TABLE_COLUMN_3 + " TEXT NOT NULL , " +
                TABLE_COLUMN_4 + " TEXT NOT NULL , " +
                TABLE_COLUMN_5 + " TEXT NOT NULL , " +
                TABLE_COLUMN_6 + " INTEGER NOT NULL , " +
                TABLE_COLUMN_7 + " INTEGER NOT NULL , " +
                TABLE_COLUMN_8 + " INTEGER NOT NULL ) "

        val QUERY_ALL = " SELECT * FROM " + TABLE_NAME


    }
}