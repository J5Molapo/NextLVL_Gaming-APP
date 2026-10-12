package com.example.enragedyetti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.enragedyetti.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClearCart.setOnClickListener {
            binding.tvSubtotal.text = "R0.00"
            binding.tvDiscount.text = "R0.00"
            binding.tvFees.text = "R0.00"
            binding.tvGrandTotal.text = "R0.00"
            Toast.makeText(context, "Cart cleared", Toast.LENGTH_SHORT).show()
        }

        binding.btnCheckout.setOnClickListener {
            findNavController().navigate(R.id.action_CartFragment_to_CheckoutFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}