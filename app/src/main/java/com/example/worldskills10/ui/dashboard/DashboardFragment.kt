package com.example.worldskills10.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldskills10.R
import com.example.worldskills10.databinding.FragmentDashboardBinding
import com.example.worldskills10.models.Constants
import com.example.worldskills10.models.webService.enviopedido.ResponseEnvioPedido
import com.example.worldskills10.models.webService.historial.ResponseHistorial
import com.example.worldskills10.repository.local.DBManager
import com.example.worldskills10.ui.DialogPedido
import com.example.worldskills10.ui.MainActivity
import com.example.worldskills10.ui.PedidoAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect

class DashboardFragment : Fragment() {

    private val  dashboardViewModel: DashboardViewModel by viewModels()
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbManager = DBManager(requireContext())
        val lista = dbManager.listData()
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PedidoAdapter(lista)
        binding.rv.adapter =adapter

        val total=dbManager.sumTotal()

        binding.txtTotal.text = "Total : $"+total
        binding.txtTotalIva.text = "Total IVA : $"+(total*1.19).toInt()




        btnFinalizar()

    }

    private fun btnFinalizar() {
        binding.btnFinalizar.setOnClickListener {
                val shared = requireActivity().getSharedPreferences("inicioSesion", Context.MODE_PRIVATE)
                val id = shared.getString(Constants.KEY_ID_USER,null)!!.toInt()
                val dbManager = DBManager(requireContext())
                val total = dbManager.sumTotal()
                val lista = dbManager.listPedido()
            
                if (lista.isEmpty()){
                    Snackbar.make(binding.root,"no se puede enviar un pedido vacio",Snackbar.LENGTH_LONG).show()
                }else{
                    lifecycleScope.launchWhenStarted {
                    dashboardViewModel.postEnvioPedido(id,lista.toString(),(total*1.19).toInt()) .collect {
                        when(it){
                            is ResponseEnvioPedido->{
                                if (it.respuesta == "OK"){
                                    val res = dbManager.deleteAll()
                                    if (res>0){
                                        val dialog = DialogPedido()
                                        dialog.show(requireActivity().supportFragmentManager,"pedido")
                                    }
                                }else{
                                    Snackbar.make(binding.root,"error al enviar pedido",Snackbar.LENGTH_LONG).show()
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