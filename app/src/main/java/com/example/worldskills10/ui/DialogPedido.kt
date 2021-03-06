package com.example.worldskills10.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import com.example.worldskills10.R
import com.example.worldskills10.databinding.DialogPedidoBinding

class DialogPedido:DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.dialog_pedido,container,false)

        val binding = DialogPedidoBinding.bind(view)

        binding.buttonOk.setOnClickListener {
            val intent = Intent(requireActivity(),MainActivity::class.java)
            startActivity(intent)
            ActivityCompat.finishAfterTransition(requireActivity())
        }

        isCancelable = false

        return binding.root
    }
}