package com.example.worldskills10.ui.iniciosesion

import androidx.lifecycle.ViewModel
import com.example.worldskills10.interfaces.ApiClient
import com.example.worldskills10.repository.webservice.RepoInicioSesion
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class InicioSesionViewModel :ViewModel(){
    private val repoInicioSesion = RepoInicioSesion(ApiClient.webService)
    suspend fun getInicioSesion(correo:String,contrasena:String) = flow {
        try {
            emit(repoInicioSesion.getInicioSesion(correo,contrasena))
        }catch (e:Exception){
            emit(e)
        }
    }

}