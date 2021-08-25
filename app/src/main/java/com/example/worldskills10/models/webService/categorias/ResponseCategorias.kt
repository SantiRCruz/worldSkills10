package com.example.worldskills10.models.webService.categorias

data class ResponseCategorias(
    var respuesta:String =  "OK",
    var datos:ArrayList<Datos> = arrayListOf()
)
