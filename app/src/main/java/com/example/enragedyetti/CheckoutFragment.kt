package com.example.enragedyetti

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.enragedyetti.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {
    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            if (account.isNotEmpty() && name.isNotEmpty()) {
                findNavController().navigate(R.id.action_CheckoutFragment_to_PaymentSuccessfulFragment)
            } else {
                Toast.makeText(context, "Please fill in all payment details", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun selectPaymentMethod(methodName: String, detailsLabel: String, hintText: String) {
        binding.btnPayCapitec.isSelected = (methodName == "Capitec pay")
        binding.btnPayOzow.isSelected = (methodName == "Ozow")
        binding.btnPayCard.isSelected = (methodName == "Card")

        binding.tvPaymentDetailsLabel.text = detailsLabel
        binding.etPaymentAccount.hint = hintText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}