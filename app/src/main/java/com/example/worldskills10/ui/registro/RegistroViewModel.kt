package com.example.worldskills10.ui.registro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldskills10.interfaces.ApiClient
import com.example.worldskills10.models.webService.registro.BodyRegistro
import com.example.worldskills10.repository.webservice.RepoRegistro
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class RegistroViewModel:ViewModel() {

    private val repoRegistro = RepoRegistro(ApiClient.webService)

    suspend fun getPoliticas() = flow {
        try {
            emit(repoRegistro.getPoliticas())
        }catch (e : Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

    suspend fun postRegistro(nombre:String,correo:String,contrasena:String,ciudad:String) = flow {
        try {
            emit(repoRegistro.postRegistro(BodyRegistro(nombre,correo,contrasena,ciudad)))
        }catch (e : Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)
}