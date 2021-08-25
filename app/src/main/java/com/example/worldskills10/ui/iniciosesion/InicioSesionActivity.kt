package com.example.worldskills10.ui.iniciosesion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.worldskills10.ui.especialidad.EspecialidadActivity
import com.example.worldskills10.ui.registro.RegistroActivity
import com.example.worldskills10.databinding.ActivityInicioSesionBinding
import com.example.worldskills10.models.Constants
import com.example.worldskills10.models.webService.iniciosesion.ResponseInicioSesion
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect

class InicioSesionActivity : AppCompatActivity() {
    private val inicioSesionViewModel : InicioSesionViewModel by viewModels()
    private lateinit var binding :ActivityInicioSesionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        btnInicioSesion()
        btnRegistro()

    }

    private fun btnInicioSesion() {
        binding.btnInicioSesion.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                inicioSesionViewModel.getInicioSesion(binding.etCorreo.text.toString(),binding.etContrasena.text.toString()).collect {
                    when(it){
                        is ResponseInicioSesion ->{
                            if (it.respuesta == "OK"){
                                saveData()
                                saveDataImp(it.idCliente.toString(),it.token,it.nombre)
                                val intent = Intent(applicationContext, EspecialidadActivity::class.java)
                                startActivity(intent)
                                finish()
                            }else{
                                Snackbar.make(binding.root,"Error al iniciar Sesion",Snackbar.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun saveDataImp(id:String,token:String,nombre:String) {
        val shared = getSharedPreferences("inicioSesion",Context.MODE_PRIVATE)
        val edit = shared.edit()
        edit.apply {
            putString(Constants.KEY_ID_USER,id)
            putString(Constants.KEY_TOKEN,token)
            putString(Constants.KEY_USER_NAME,nombre)
        }.apply()

    }

    private fun loadData() {
        val shared = getSharedPreferences("inicioSesion",Context.MODE_PRIVATE)
        val correo = shared.getString(Constants.KEY_CORREO,null)
        val contrasena = shared.getString(Constants.KEY_CONTRASENA,null)
        val recordar = shared.getBoolean(Constants.KEY_RECORDAR,false)
        binding.etCorreo.setText(correo)
        binding.etContrasena.setText(contrasena)
        if (recordar)binding.cbRecordar.isChecked = true
    }

    private fun saveData() {
        val shared = getSharedPreferences("inicioSesion",Context.MODE_PRIVATE)
        val edit = shared.edit()
        if (binding.cbRecordar.isChecked){
            edit.apply {
                putString(Constants.KEY_CORREO,binding.etCorreo.text.toString())
                putString(Constants.KEY_CONTRASENA,binding.etContrasena.text.toString())
                putBoolean(Constants.KEY_RECORDAR,true)
            }.apply()
        }else if(!binding.cbRecordar.isChecked){
            edit.apply {
                putString(Constants.KEY_CORREO,"")
                putString(Constants.KEY_CONTRASENA,"")
                putBoolean(Constants.KEY_RECORDAR,false)
            }.apply()
        }
    }

    private fun btnRegistro() {
        binding.btnRegistro.setOnClickListener {
            val intent = Intent(applicationContext, RegistroActivity::class.java)
            startActivity(intent)
        }
    }


}