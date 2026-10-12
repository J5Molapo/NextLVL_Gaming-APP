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

        binding.btnAddUltimate.setOnClickListener { addToCart("Ultimate Gamer Pass (R499.00)") }
        binding.btnAddVip.setOnClickListener { addToCart("VIP Gaming Experience (R799.00)") }
        binding.btnAddEsports.setOnClickListener { addToCart("Esports Training Packages (R599.00)") }
        binding.btnAddBirthday.setOnClickListener { addToCart("Birthday Party Packages (R1,299.00)") }

        binding.btnAddVr.setOnClickListener { addToCart("Virtual Reality (R149.00)") }
        binding.btnAddRacing.setOnClickListener { addToCart("Racing Simulator Challenge (R179.00)") }
        binding.btnAddEscape.setOnClickListener { addToCart("Escape Room Challenge (R199.00)") }

        binding.btnAddHoodie.setOnClickListener { addToCart("Hoodie (R599.00)") }
        binding.btnAddShirts.setOnClickListener { addToCart("Merch Shirts (R249.00)") }
        binding.btnAddKeyboard.setOnClickListener { addToCart("Keyboard Set (R1,299.00)") }
        binding.btnAddPuffer.setOnClickListener { addToCart("Enraged PufferJacket (R1,799.00)") }
    }

    private fun addToCart(itemName: String) {
        Toast.makeText(context, "Added $itemName to cart!", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}