package com.example.enragedyetti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enragedyetti.databinding.FragmentCartBinding
import java.util.Locale

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CartAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CartAdapter(CartManager.items) {
            updateSummary()
        }
        binding.cartRecyclerView.adapter = adapter

        updateSummary()

        binding.btnClearCart.setOnClickListener {
            if (CartManager.items.isNotEmpty()) {
                CartManager.clear()
                adapter.notifyDataSetChanged()
                updateSummary()
                Toast.makeText(context, "Cart cleared", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Cart is already empty", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCheckout.setOnClickListener {
            if (CartManager.items.isEmpty()) {
                Toast.makeText(context, "Your cart is empty! Add items from Products.", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.action_CartFragment_to_CheckoutFragment)
            }
        }
    }

    private fun updateSummary() {
        val subtotal = CartManager.getSubtotal()
        val discount = CartManager.getDiscount()
        val discountPercent = CartManager.getDiscountPercent()
        val fees = CartManager.getFees()
        val grandTotal = CartManager.getGrandTotal()

        binding.tvSubtotal.text = String.format(Locale.US, "R%.2f", subtotal)
        binding.tvDiscountLabel.text = if (discountPercent > 0) "Discount ($discountPercent%):" else "Discount:"
        binding.tvDiscount.text = String.format(Locale.US, "-R%.2f", discount)
        binding.tvFees.text = String.format(Locale.US, "R%.2f", fees)
        binding.tvGrandTotal.text = String.format(Locale.US, "R%.2f", grandTotal)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}