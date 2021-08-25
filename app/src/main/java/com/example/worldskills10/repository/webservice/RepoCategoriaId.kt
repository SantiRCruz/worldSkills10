package com.example.worldskills10.repository.webservice

import com.example.worldskills10.interfaces.ApiService
import com.example.worldskills10.models.webService.categoriaid.ResponseCategoriaId

class RepoCategoriaId(val apiService: ApiService) {

    suspend fun getCategoriaId(idCategoria:Int):ResponseCategoriaId = apiService.getCategorias(idCategoria)

}