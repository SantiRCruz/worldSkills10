package com.example.worldskills10.models.webService.enviopedido

data class BodyEnvioPedido(
    var id_cliente:Int=0,
    var json_pedido:String =  "",
    var total:Int=0
)