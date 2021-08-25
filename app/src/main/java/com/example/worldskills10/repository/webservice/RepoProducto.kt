package com.example.worldskills10.repository.webservice

import com.example.worldskills10.interfaces.ApiService
import com.example.worldskills10.models.webService.producto.ResponseProducto

class RepoProducto(val apiService: ApiService) {

    suspend fun getProducto(idProductos:Int):ResponseProducto = apiService.getProducto(idProductos)

}