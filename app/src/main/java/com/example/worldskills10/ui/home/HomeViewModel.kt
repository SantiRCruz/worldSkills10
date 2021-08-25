package com.example.worldskills10.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldskills10.interfaces.ApiClient
import com.example.worldskills10.repository.webservice.RepoCategorias
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import java.lang.Exception

class HomeViewModel : ViewModel() {

    private val repoCategorias = RepoCategorias(ApiClient.webService)

    suspend fun getCategorias() = flow {
        try {
            emit(repoCategorias.getCategorias())
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)
}