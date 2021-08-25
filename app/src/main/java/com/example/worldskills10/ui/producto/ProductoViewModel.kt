package com.example.worldskills10.ui.producto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldskills10.interfaces.ApiClient
import com.example.worldskills10.repository.webservice.RepoProducto
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import java.lang.Exception

class ProductoViewModel :ViewModel(){

    private val repoProducto = RepoProducto(ApiClient.webService)

    suspend fun getProducto(idProductos:Int) = flow {
        try {
            emit(repoProducto.getProducto(idProductos))
        }catch (e : Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)
}