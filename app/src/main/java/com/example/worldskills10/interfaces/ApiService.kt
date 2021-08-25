package com.example.worldskills10.interfaces

import com.example.worldskills10.models.webService.categoriaid.ResponseCategoriaId
import com.example.worldskills10.models.webService.categorias.ResponseCategorias
import com.example.worldskills10.models.webService.enviopedido.BodyEnvioPedido
import com.example.worldskills10.models.webService.enviopedido.ResponseEnvioPedido
import com.example.worldskills10.models.webService.especialidad.ResponseEspecialidad
import com.example.worldskills10.models.webService.historial.ResponseHistorial
import com.example.worldskills10.models.webService.iniciosesion.ResponseInicioSesion
import com.example.worldskills10.models.webService.politicas.ResponsePoliticas
import com.example.worldskills10.models.webService.producto.ResponseProducto
import com.example.worldskills10.models.webService.registro.BodyRegistro
import com.example.worldskills10.models.webService.registro.ResponseRegistro
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    @POST("pedidos")
    suspend fun postEnvioPedido(@Body bodyEnvioPedido: BodyEnvioPedido  ): ResponseEnvioPedido

    @GET("pedidos")
    suspend fun getHistorial(@Query("cliente")cliente:String,@Query("token")token:String): ResponseHistorial

    @GET("productos/{idProductos}")
    suspend fun getProducto(@Path("idProductos")idProductos:Int): ResponseProducto

    @GET("categorias/{idCategoria}")
    suspend fun getCategorias(@Path("idCategoria")idCategoria:Int): ResponseCategoriaId

    @GET("categorias")
    suspend fun getCategorias(): ResponseCategorias

    @GET("especialidad")
    suspend fun getEspecilidad():ResponseEspecialidad

    @POST("clientes")
    suspend fun postRegistro(@Body bodyRegistro: BodyRegistro ): ResponseRegistro

    @GET("politicas?ver")
    suspend fun getPoliticas(): ResponsePoliticas

    @GET("clientes")
    suspend fun getInicioSesion(@Query("correo")correo:String,@Query("contrasena")contrasena:String): ResponseInicioSesion

}
object ApiClient{
    val webService by lazy {
        Retrofit.Builder()
            .baseUrl("https://wsc.fabricasoftware.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}