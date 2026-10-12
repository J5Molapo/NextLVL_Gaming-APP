package com.example.enragedyetti

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.enragedyetti.databinding.FragmentCheckoutBinding
import java.util.Locale

class CheckoutFragment : Fragment() {
    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!
    private var currentMethod = "Capitec pay"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Display current total from CartManager
        val grandTotal = CartManager.getGrandTotal()
        binding.tvTotalLabel.text = String.format(Locale.US, "Total: R%.2f", grandTotal)

        // Select default payment method
        selectPaymentMethod("Capitec pay", "Capitec Pay details", "Capitec mobile number")

        binding.btnPayCapitec.setOnClickListener {
            selectPaymentMethod("Capitec pay", "Capitec Pay details", "Capitec mobile number")
        }

        binding.btnPayOzow.setOnClickListener {
            selectPaymentMethod("Ozow", "Ozow Account details", "Ozow Account ID")
        }

        binding.btnPayCard.setOnClickListener {
            selectPaymentMethod("Card", "Credit/Debit Card details", "Card Number")
        }

        binding.btnPayNow.setOnClickListener {
            val account = binding.etPaymentAccount.text.toString().trim()
            val name = binding.etFullName.text.toString().trim()

            var isValid = true

            if (account.isEmpty()) {
                binding.etPaymentAccount.error = "Payment account details required"
                isValid = false
            } else if (currentMethod == "Capitec pay" && (account.length < 10 || !account.all { it.isDigit() })) {
                binding.etPaymentAccount.error = "Please enter a valid 10-digit mobile number"
                isValid = false
            } else {
                binding.etPaymentAccount.error = null
            }

            if (name.isEmpty()) {
                binding.etFullName.error = "Full name is required"
                isValid = false
            } else if (name.length < 3) {
                binding.etFullName.error = "Please enter your complete full name"
                isValid = false
            } else {
                binding.etFullName.error = null
            }

            if (isValid) {
                CartManager.clear()
                findNavController().navigate(R.id.action_CheckoutFragment_to_PaymentSuccessfulFragment)
            } else {
                Toast.makeText(context, "Please fix the errors above to complete payment", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun selectPaymentMethod(methodName: String, detailsLabel: String, hintText: String) {
        currentMethod = methodName
        binding.btnPayCapitec.isSelected = (methodName == "Capitec pay")
        binding.btnPayOzow.isSelected = (methodName == "Ozow")
        binding.btnPayCard.isSelected = (methodName == "Card")

        binding.tvPaymentDetailsLabel.text = detailsLabel
        binding.etPaymentAccount.hint = hintText
        binding.etPaymentAccount.error = null
        binding.etFullName.error = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}