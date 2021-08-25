package com.example.worldskills10.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldskills10.interfaces.ApiClient
import com.example.worldskills10.models.webService.enviopedido.BodyEnvioPedido
import com.example.worldskills10.repository.webservice.RepoEnvioPedido
import com.example.worldskills10.repository.webservice.RepoHistorial
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class DashboardViewModel : ViewModel() {

    private val repo = RepoEnvioPedido(ApiClient.webService)

    suspend fun postEnvioPedido(id_cliente:Int,json_pedido:String,total:Int)= flow {
        try {
            emit(repo.postEnvioPedido(BodyEnvioPedido(id_cliente,json_pedido,total)))
        }catch (e:Exception){
            emit(e)
        }
    }
}