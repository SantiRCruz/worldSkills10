package com.example.worldskills10.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldskills10.R
import com.example.worldskills10.databinding.ItemCategoriaIdBinding
import com.example.worldskills10.models.Constants
import com.example.worldskills10.models.local.DBPedido
import com.example.worldskills10.models.webService.categoriaid.Productos
import com.example.worldskills10.repository.local.DBManager

class CategoriaIdAdapter(val categoriaId : List<Productos>): RecyclerView.Adapter<CategoriaIdAdapter.CategoriaIdHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaIdHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoriaIdHolder(layoutInflater.inflate(R.layout.item_categoria_id,parent,false),parent.context)
    }

    override fun onBindViewHolder(holder: CategoriaIdHolder, position: Int) {
        holder.bind(categoriaId[position])
    }

    override fun getItemCount(): Int = categoriaId.size

    class CategoriaIdHolder(val view : View,val context:Context): RecyclerView.ViewHolder(view){
        private val binding = ItemCategoriaIdBinding.bind(view)
        val dbManager = DBManager(context)
        fun bind(categoriaId: Productos){
            binding.txtNombre.text = categoriaId.nombre
            binding.txtDescripcion.text = categoriaId.descripcion
            binding.txtPrecio.text = "$"+categoriaId.precio
            Glide.with(binding.root).load(categoriaId.url_imagen).into(binding.img)
            binding.root.setOnClickListener {
                Constants.ID_PRODUCTO = categoriaId.id
                Navigation.findNavController(binding.root).navigate(R.id.navigation_producto)
            }
            binding.buttonAgregar.setOnClickListener {
                val lista = dbManager.listAcumulacion(categoriaId.id)
                if (lista.isEmpty()){
                    val res = dbManager.insertData(DBPedido(0,categoriaId.id,categoriaId.nombre,categoriaId.descripcion,categoriaId.url_imagen,categoriaId.precio,categoriaId.precio,1))
                    if (res>0){
                        Navigation.findNavController(binding.root).navigate(R.id.navigation_dashboard)
                    }
                }else{
                    val res = dbManager.updateData(lista[0].id,lista[0].precioUnidad*(lista[0].cantidad+1),lista[0].cantidad+1)
                    if (res>0){
                        Navigation.findNavController(binding.root).navigate(R.id.navigation_dashboard)
                    }
                }
            }
        }
    }
}