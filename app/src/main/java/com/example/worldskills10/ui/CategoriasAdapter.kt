package com.example.worldskills10.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldskills10.R
import com.example.worldskills10.databinding.ItemCategoriasBinding
import com.example.worldskills10.models.Constants
import com.example.worldskills10.models.webService.categorias.Datos

class CategoriasAdapter(val categoria : List<Datos>):RecyclerView.Adapter<CategoriasAdapter.CategoriasHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriasHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoriasHolder(layoutInflater.inflate(R.layout.item_categorias,parent,false))
    }

    override fun onBindViewHolder(holder: CategoriasHolder, position: Int) {
        holder.bind(categoria[position])
    }

    override fun getItemCount(): Int = categoria.size

    class CategoriasHolder(val view : View):RecyclerView.ViewHolder(view){
        private val binding = ItemCategoriasBinding.bind(view)
        fun bind(categoria: Datos){
            binding.txtNombre.text = categoria.nombre
            binding.txtDescipcion.text = categoria.descripcion
            Glide.with(binding.root).load(categoria.url_imagen).into(binding.imageView3)
            binding.root.setOnClickListener {
                Constants.ID_CATEGORIA = categoria.id
                Navigation.findNavController(binding.root).navigate(R.id.navigation_categoria_id)
            }
        }
    }
}