package com.example.worldskills10.ui.categoriaid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldskills10.interfaces.ApiClient
import com.example.worldskills10.repository.webservice.RepoCategoriaId
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import java.lang.Exception

class CategoriaIdViewModel:ViewModel() {

    private val repoCategoriaId = RepoCategoriaId(ApiClient.webService)

    suspend fun getCategoriaId(idCategoria:Int)= flow {
        try {
            emit(repoCategoriaId.getCategoriaId(idCategoria))
        }catch (e :Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

}