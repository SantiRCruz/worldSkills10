package com.example.worldskills10.ui.categoriaid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldskills10.databinding.FragmentCategoriaIdBinding
import com.example.worldskills10.models.Constants
import com.example.worldskills10.models.webService.categoriaid.ResponseCategoriaId
import com.example.worldskills10.ui.CategoriaIdAdapter
import kotlinx.coroutines.flow.collect


class CategoriaIdFragment : Fragment() {
    private val categoriaIdViewModel: CategoriaIdViewModel by viewModels()
    private var _binding: FragmentCategoriaIdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentCategoriaIdBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvList()
    }

    private fun rvList() {
        lifecycleScope.launchWhenStarted {
            categoriaIdViewModel.getCategoriaId(Constants.ID_CATEGORIA).collect {
                when(it){
                    is ResponseCategoriaId ->{
                        binding.txtNombre.text = it.nombre
                        binding.rv.layoutManager = LinearLayoutManager(requireContext())
                        val adapter = CategoriaIdAdapter(it.productos)
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