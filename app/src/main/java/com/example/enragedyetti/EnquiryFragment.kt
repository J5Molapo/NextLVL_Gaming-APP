package com.example.enragedyetti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.enragedyetti.databinding.FragmentEnquiryBinding

class EnquiryFragment : Fragment() {
    private var _binding: FragmentEnquiryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEnquiryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Populate Interested In Dropdown
        val productTypes = arrayOf(
            "Gaming Headset",
            "Mechanical Keyboard",
            "Gaming Mouse",
            "Gaming Chair",
            "Custom Setup"
        )
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, productTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerProductType.adapter = adapter

        binding.btnSubmitEnquiry.setOnClickListener {
            val firstName = binding.etFirstName.text.toString().trim()
            val enquiryMsg = binding.etEnquiryMessage.text.toString().trim()

            if (firstName.isNotEmpty() && enquiryMsg.isNotEmpty()) {
                Toast.makeText(context, "Thank you, $firstName! Your enquiry has been submitted.", Toast.LENGTH_LONG).show()
                binding.etFirstName.text?.clear()
                binding.etLastName.text?.clear()
                binding.etEnquiryPhone.text?.clear()
                binding.etEnquiryMessage.text?.clear()
            } else {
                Toast.makeText(context, "Please enter your first name and enquiry details.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}