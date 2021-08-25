package com.example.worldskills10.ui.especialidad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.worldskills10.databinding.ActivityEspecialidadBinding
import com.example.worldskills10.models.webService.especialidad.ResponseEspecialidad
import com.example.worldskills10.ui.MainActivity
import kotlinx.coroutines.flow.collect

class EspecialidadActivity : AppCompatActivity() {
    private val especialidadViewModel : EspecialidadViewModel by viewModels()
    private lateinit var binding: ActivityEspecialidadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEspecialidadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        referenciar()
        btnContinuar()

    }

    private fun referenciar() {
        lifecycleScope.launchWhenStarted {
            especialidadViewModel.getEspecialidad().collect {
                when(it){
                    is ResponseEspecialidad ->{
                        binding.txtNombre.text = it.datos.nombre
                        binding.txtDescripcion.text = it.datos.descripcion
                        binding.txtPrecio.text = "$"+it.datos.precio
                        Glide.with(binding.root).load(it.datos.url_foto).into(binding.imageView2)
                    }
                }
            }
        }
    }

    private fun btnContinuar() {
        binding.btnContinuar.setOnClickListener {
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}