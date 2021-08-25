package com.example.worldskills10.repository.webservice

import com.example.worldskills10.interfaces.ApiService
import com.example.worldskills10.models.webService.categorias.ResponseCategorias

class RepoCategorias(val apiService: ApiService) {

    suspend fun getCategorias():ResponseCategorias = apiService.getCategorias()


}