package com.example.worldskills10.models.webService.historial

data class ResponseHistorial(
    var respuesta:String =  "",
    var pedidos:ArrayList<Pedidos> =  arrayListOf()
)
