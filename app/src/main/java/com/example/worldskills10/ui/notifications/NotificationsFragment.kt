package com.example.worldskills10.ui.notifications

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldskills10.R
import com.example.worldskills10.databinding.FragmentNotificationsBinding
import com.example.worldskills10.models.Constants
import com.example.worldskills10.models.webService.historial.ResponseHistorial
import com.example.worldskills10.ui.HistorialAdapter
import kotlinx.coroutines.flow.collect

class NotificationsFragment : Fragment() {

    private val  notificationsViewModel: NotificationsViewModel by viewModels()
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            val shared = requireActivity().getSharedPreferences("inicioSesion", Context.MODE_PRIVATE)
            val id = shared.getString(Constants.KEY_ID_USER,null)
            val token = shared.getString(Constants.KEY_TOKEN,null)
            notificationsViewModel.getHistorial(id.toString(),token.toString()).collect {
                when(it){
                    is ResponseHistorial ->{
                        binding.rv.layoutManager = LinearLayoutManager(requireContext())
                        val adapter = HistorialAdapter(it.pedidos)
                        binding.rv.adapter = adapter
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