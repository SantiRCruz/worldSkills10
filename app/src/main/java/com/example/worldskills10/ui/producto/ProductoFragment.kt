package com.example.worldskills10.ui.producto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.worldskills10.R
import com.example.worldskills10.databinding.FragmentProductoBinding
import com.example.worldskills10.models.Constants
import com.example.worldskills10.models.local.DBPedido
import com.example.worldskills10.models.webService.producto.ResponseProducto
import com.example.worldskills10.repository.local.DBManager
import kotlinx.coroutines.flow.collect

class ProductoFragment : Fragment() {
    private val productoViewModel: ProductoViewModel by viewModels()
    private var _binding: FragmentProductoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductoBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvList()
    }

    private fun rvList() {
        lifecycleScope.launchWhenStarted {
            productoViewModel.getProducto(Constants.ID_PRODUCTO).collect {data ->
                when(data){
                    is ResponseProducto ->{
                        binding.txtNombre.text = data.productos.nombre
                        binding.txtDescripcion.text = data.productos.descripcion
                        binding.txtPrecio.text = "$"+data.productos.precio
                        Glide.with(binding.root).load(data.productos.url_imagen).into(binding.img)
                       binding.buttonAgregar.setOnClickListener {
                            val dbManager = DBManager(requireContext())
                           val lista = dbManager.listAcumulacion(data.productos.id)
                           if (lista.isEmpty()){
                               val res = dbManager.insertData(DBPedido(0,data.productos.id,data.productos.nombre,data.productos.descripcion,data.productos.url_imagen,data.productos.precio,data.productos.precio,1))
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
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}