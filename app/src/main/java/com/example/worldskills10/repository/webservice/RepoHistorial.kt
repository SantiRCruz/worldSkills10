package com.example.worldskills10.repository.webservice

import com.example.worldskills10.interfaces.ApiService
import com.example.worldskills10.models.webService.historial.ResponseHistorial

class RepoHistorial (val apiService: ApiService){

    suspend fun gteHistorial(cliente:String,token:String):ResponseHistorial = apiService.getHistorial(cliente,token)

}