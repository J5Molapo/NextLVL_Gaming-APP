package com.example.enragedyetti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.enragedyetti.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddUltimate.setOnClickListener {
            CartManager.addItem(CartItem("Ultimate Gamer Pass", 499.0, R.drawable.memberships_pic))
            Toast.makeText(context, "Added Ultimate Gamer Pass to Cart!", Toast.LENGTH_SHORT).show()
        }
        binding.btnAddVip.setOnClickListener {
            CartManager.addItem(CartItem("VIP Gaming Experience", 799.0, R.drawable.memberships_pic))
            Toast.makeText(context, "Added VIP Gaming Experience to Cart!", Toast.LENGTH_SHORT).show()
        }
        binding.btnAddEsports.setOnClickListener {
            CartManager.addItem(CartItem("Esports Training Package", 599.0, R.drawable.memberships_pic))
            Toast.makeText(context, "Added Esports Training Package to Cart!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}