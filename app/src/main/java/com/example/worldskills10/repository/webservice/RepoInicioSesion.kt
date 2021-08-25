package com.example.worldskills10.repository.webservice

import com.example.worldskills10.interfaces.ApiService
import com.example.worldskills10.models.webService.iniciosesion.ResponseInicioSesion

class RepoInicioSesion(val apiService: ApiService) {

    suspend fun getInicioSesion(correo:String,contrasena:String):ResponseInicioSesion = apiService.getInicioSesion(correo,contrasena)

}