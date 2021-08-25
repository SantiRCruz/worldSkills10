package com.example.worldskills10.ui.registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.worldskills10.databinding.ActivityRegistroBinding
import com.example.worldskills10.models.webService.politicas.ResponsePoliticas
import com.example.worldskills10.models.webService.registro.ResponseRegistro
import com.example.worldskills10.ui.especialidad.EspecialidadActivity
import com.example.worldskills10.ui.iniciosesion.InicioSesionActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect

class RegistroActivity : AppCompatActivity() {
    private val registroViewModel : RegistroViewModel by viewModels()
    private lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnRegistro()
        btnInicioSesion()

    }

    private fun btnRegistro() {
        binding.btnRegistro.setOnClickListener {
            if (binding.etNombre.text.toString().isEmpty()||binding.etCorreo.text.toString().isEmpty()||binding.etContrasena.text.toString().isEmpty()||binding.etCiudad.text.toString().isEmpty()){
                Snackbar.make(binding.root,"debe completar los datos",Snackbar.LENGTH_LONG).show()
            }else{
                lifecycleScope.launchWhenStarted {
                    registroViewModel.getPoliticas().collect {
                        when(it){
                            is ResponsePoliticas ->{
                                val dialog = AlertDialog.Builder(this@RegistroActivity)
                                    .setTitle("políticas de privacidad y protección de datos")
                                    .setMessage(it.datos.politicas)
                                    .setNegativeButton("Cancelar"){Dialog, with->
                                        Snackbar.make(binding.root,"no se pudo crear la cuenta",Snackbar.LENGTH_LONG).show()
                                    }
                                    .setPositiveButton("Aceptar"){Dialog, with->
                                        lifecycleScope.launchWhenStarted {
                                            registroViewModel.postRegistro(binding.etNombre.text.toString(),binding.etCorreo.text.toString(),binding.etContrasena.text.toString(),binding.etCiudad.text.toString()).collect {
                                                when(it){
                                                    is ResponseRegistro ->{
                                                        if (it.respuesta == "OK"){
                                                            val intent = Intent(applicationContext,
                                                                EspecialidadActivity::class.java)
                                                            startActivity(intent)
                                                            finish()
                                                        }else{
                                                            Snackbar.make(binding.root,it.mensaje,Snackbar.LENGTH_LONG).show()
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    .setCancelable(false)
                                    .create()
                                dialog.show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun btnInicioSesion() {
        binding.btnInicioSesion.setOnClickListener {
            val intent = Intent(applicationContext, InicioSesionActivity::class.java)
            startActivity(intent)
        }
    }

}