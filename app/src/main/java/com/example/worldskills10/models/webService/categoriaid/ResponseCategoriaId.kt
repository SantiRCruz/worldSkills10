package com.example.worldskills10.models.webService.categoriaid

data class ResponseCategoriaId(
    var respuesta:String = "",
    var nombre:String = "",
    var descripcion:String = "",
    var productos:ArrayList<Productos> = arrayListOf()
)