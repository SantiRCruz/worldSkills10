package com.example.worldskills10.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.worldskills10.databinding.FragmentHomeBinding
import com.example.worldskills10.models.webService.categorias.ResponseCategorias
import com.example.worldskills10.ui.CategoriasAdapter
import kotlinx.coroutines.flow.collect

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvList()
    }

    private fun rvList() {
        lifecycleScope.launchWhenStarted {
            homeViewModel.getCategorias().collect {
                when(it){
                    is ResponseCategorias ->{
                        binding.rv.layoutManager = GridLayoutManager(requireContext(),2)
                        val adapter =CategoriasAdapter(it.datos)
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