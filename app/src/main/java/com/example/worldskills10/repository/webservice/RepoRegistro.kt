package com.example.worldskills10.repository.webservice

import com.example.worldskills10.interfaces.ApiService
import com.example.worldskills10.models.webService.politicas.ResponsePoliticas
import com.example.worldskills10.models.webService.registro.BodyRegistro
import com.example.worldskills10.models.webService.registro.ResponseRegistro

class RepoRegistro (val apiService: ApiService){

    suspend fun getPoliticas():ResponsePoliticas = apiService.getPoliticas()

    suspend fun postRegistro(bodyRegistro: BodyRegistro):ResponseRegistro = apiService.postRegistro(bodyRegistro)

}