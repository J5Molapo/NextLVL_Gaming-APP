package com.example.enragedyetti

import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
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

        // Render dynamic items in basket from CartManager
        renderSelectedBasketItems()

        // Select default payment method (Capitec)
        selectPaymentMethod("Capitec pay")

        binding.btnPayCapitec.setOnClickListener {
            selectPaymentMethod("Capitec pay")
        }

        binding.btnPayOzow.setOnClickListener {
            selectPaymentMethod("Ozow")
        }

        binding.btnPayCard.setOnClickListener {
            selectPaymentMethod("Card")
        }

        binding.btnPayNow.setOnClickListener {
            if (CartManager.items.isEmpty()) {
                Toast.makeText(context, "Your cart is empty! Select items from Products first.", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.ProductsFragment)
                return@setOnClickListener
            }

            if (validatePaymentDetails()) {
                // Save Order Record into UserSessionManager
                val grandTotal = CartManager.getGrandTotal()
                val orderId = UserSessionManager.generateOrderId()
                val order = OrderHistory(
                    id = orderId,
                    total = grandTotal,
                    items = ArrayList(CartManager.items),
                    timestamp = UserSessionManager.getCurrentTimestamp()
                )
                UserSessionManager.addOrder(order)

                // Trigger interactive order confirmation email
                val recipientEmail = when (currentMethod) {
                    "Ozow" -> binding.etOzowEmail.text.toString().trim()
                    else -> UserSessionManager.currentUser?.email ?: ""
                }
                context?.let { ctx ->
                    UserSessionManager.sendOrderEmailIntent(ctx, recipientEmail, orderId, grandTotal)
                }

                // Clear active cart after successful checkout
                CartManager.clear()

                Toast.makeText(context, "Payment successful! $orderId placed.", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_CheckoutFragment_to_PaymentSuccessfulFragment)
            }
        }
    }

    private fun renderSelectedBasketItems() {
        val container = binding.checkoutItemsContainer
        container.removeAllViews()

        val selectedItems = CartManager.items

        if (selectedItems.isEmpty()) {
            val emptyTv = TextView(context).apply {
                text = "No items selected. Add items from Products page."
                setTextColor(Color.parseColor("#A0A0B0"))
                textSize = 14f
                setPadding(0, 12, 0, 12)
            }
            container.addView(emptyTv)
            binding.tvCheckoutTotal.text = "R0.00"
            binding.btnPayNow.text = "Pay Now"
            return
        }

        for (item in selectedItems) {
            val rowView = LinearLayout(context).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(0, 10, 0, 10)
                gravity = android.view.Gravity.CENTER_VERTICAL
            }

            val iv = ImageView(context).apply {
                layoutParams = LinearLayout.LayoutParams(40, 40)
                setImageResource(item.imageRes)
                scaleType = ImageView.ScaleType.CENTER_CROP
            }

            val tvName = TextView(context).apply {
                layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f).apply {
                    setMargins(12, 0, 0, 0)
                }
                text = item.name
                setTextColor(Color.WHITE)
                textSize = 14f
            }

            val tvPrice = TextView(context).apply {
                text = String.format(Locale.US, "R%.2f", item.price)
                setTextColor(Color.parseColor("#00FF66"))
                textSize = 14f
                typeface = android.graphics.Typeface.DEFAULT_BOLD
            }

            rowView.addView(iv)
            rowView.addView(tvName)
            rowView.addView(tvPrice)

            container.addView(rowView)
        }

        val grandTotal = CartManager.getGrandTotal()
        binding.tvCheckoutTotal.text = String.format(Locale.US, "R%.2f", grandTotal)
        binding.btnPayNow.text = String.format(Locale.US, "Pay R%.2f Now", grandTotal)
    }

    private fun selectPaymentMethod(methodName: String) {
        currentMethod = methodName
        binding.btnPayCapitec.isSelected = (methodName == "Capitec pay")
        binding.btnPayOzow.isSelected = (methodName == "Ozow")
        binding.btnPayCard.isSelected = (methodName == "Card")

        binding.layoutCapitecDetails.visibility = if (methodName == "Capitec pay") View.VISIBLE else View.GONE
        binding.layoutOzowDetails.visibility = if (methodName == "Ozow") View.VISIBLE else View.GONE
        binding.layoutCardDetails.visibility = if (methodName == "Card") View.VISIBLE else View.GONE
    }

    private fun validatePaymentDetails(): Boolean {
        return when (currentMethod) {
            "Capitec pay" -> {
                val mobile = binding.etCapitecMobile.text.toString().trim()
                val name = binding.etCapitecName.text.toString().trim()
                var valid = true

                if (mobile.isEmpty() || mobile.length < 10 || !mobile.all { it.isDigit() }) {
                    binding.etCapitecMobile.error = "Please enter a valid 10-digit Capitec mobile number"
                    valid = false
                } else {
                    binding.etCapitecMobile.error = null
                }

                if (name.isEmpty() || name.length < 3) {
                    binding.etCapitecName.error = "Full name on Capitec account required"
                    valid = false
                } else {
                    binding.etCapitecName.error = null
                }

                valid
            }
            "Ozow" -> {
                val mobile = binding.etOzowMobile.text.toString().trim()
                val email = binding.etOzowEmail.text.toString().trim()
                var valid = true

                if (mobile.isEmpty() || mobile.length < 10 || !mobile.all { it.isDigit() }) {
                    binding.etOzowMobile.error = "Please enter a valid 10-digit Ozow mobile number"
                    valid = false
                } else {
                    binding.etOzowMobile.error = null
                }

                if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.etOzowEmail.error = "Please enter a valid Ozow email address"
                    valid = false
                } else {
                    binding.etOzowEmail.error = null
                }

                valid
            }
            "Card" -> {
                val cardNum = binding.etCardNumber.text.toString().trim()
                val expiry = binding.etCardExpiry.text.toString().trim()
                val cvv = binding.etCardCvv.text.toString().trim()
                val name = binding.etCardName.text.toString().trim()
                var valid = true

                if (cardNum.isEmpty() || cardNum.length < 16 || !cardNum.all { it.isDigit() }) {
                    binding.etCardNumber.error = "Please enter a valid 16-digit card number"
                    valid = false
                } else {
                    binding.etCardNumber.error = null
                }

                if (expiry.isEmpty() || expiry.length < 4) {
                    binding.etCardExpiry.error = "Expiry MM/YY required"
                    valid = false
                } else {
                    binding.etCardExpiry.error = null
                }

                if (cvv.isEmpty() || cvv.length < 3 || !cvv.all { it.isDigit() }) {
                    binding.etCardCvv.error = "3-digit CVV required"
                    valid = false
                } else {
                    binding.etCardCvv.error = null
                }

                if (name.isEmpty() || name.length < 3) {
                    binding.etCardName.error = "Name on card required"
                    valid = false
                } else {
                    binding.etCardName.error = null
                }

                valid
            }
            else -> false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}