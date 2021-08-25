package com.example.worldskills10.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldskills10.interfaces.ApiClient
import com.example.worldskills10.repository.webservice.RepoHistorial
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class NotificationsViewModel : ViewModel() {

    private val repo = RepoHistorial(ApiClient.webService)

    suspend fun getHistorial(cliente:String,token:String) = flow {
        try {
            emit(repo.gteHistorial(cliente,token))
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)
}