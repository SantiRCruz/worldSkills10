package com.example.worldskills10.models.webService.producto

import com.example.worldskills10.models.webService.categoriaid.Productos

data class ResponseProducto (
    var respuesta:String =  "OK",
    var productos:Productos =  Productos()
        )