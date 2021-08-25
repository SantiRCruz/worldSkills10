package com.example.worldskills10.ui.especialidad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldskills10.interfaces.ApiClient
import com.example.worldskills10.repository.webservice.RepoEspecialidad
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class EspecialidadViewModel:ViewModel() {
    private val repoEspecialidad = RepoEspecialidad(ApiClient.webService)

    suspend fun getEspecialidad() = flow {
        try {
            emit(repoEspecialidad.getEspecialidad())
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

}