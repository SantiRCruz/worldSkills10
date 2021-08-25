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
import com.example.worldskills10.databinding.ItemHistorialBinding
import com.example.worldskills10.models.Constants
import com.example.worldskills10.models.local.DBPedido
import com.example.worldskills10.models.webService.categoriaid.Productos
import com.example.worldskills10.models.webService.historial.Pedidos
import com.example.worldskills10.repository.local.DBManager

class HistorialAdapter (val historial : List<Pedidos>): RecyclerView.Adapter<HistorialAdapter.HistorialHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HistorialHolder(layoutInflater.inflate(R.layout.item_historial,parent,false),parent.context)
    }

    override fun onBindViewHolder(holder: HistorialHolder, position: Int) {
        holder.bind(historial[position])
    }

    override fun getItemCount(): Int = historial.size

    class HistorialHolder(val view : View,val context: Context): RecyclerView.ViewHolder(view){
        private val binding = ItemHistorialBinding.bind(view)
        fun bind(historial: Pedidos){
            val shared = context.getSharedPreferences("inicioSesion",Context.MODE_PRIVATE)
            binding.txtId.text = historial.id.toString()
            binding.txtFecha.text = historial.created_at
            binding.txtTotal.text = historial.total.toString()
            binding.txtNombre.text = shared.getString(Constants.KEY_USER_NAME,null)

        }
    }
}