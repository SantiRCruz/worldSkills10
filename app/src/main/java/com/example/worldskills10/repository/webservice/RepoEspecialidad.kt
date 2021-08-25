package com.example.worldskills10.repository.webservice

import com.example.worldskills10.interfaces.ApiService
import com.example.worldskills10.models.webService.especialidad.ResponseEspecialidad

class RepoEspecialidad(val apiService: ApiService) {

    suspend fun getEspecialidad():ResponseEspecialidad = apiService.getEspecilidad()

}