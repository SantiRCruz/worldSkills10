package com.example.worldskills10.repository.webservice

import com.example.worldskills10.interfaces.ApiService
import com.example.worldskills10.models.webService.enviopedido.BodyEnvioPedido
import com.example.worldskills10.models.webService.enviopedido.ResponseEnvioPedido

class RepoEnvioPedido(val apiService: ApiService) {

    suspend fun postEnvioPedido(bodyEnvioPedido: BodyEnvioPedido): ResponseEnvioPedido = apiService.postEnvioPedido(bodyEnvioPedido)
}