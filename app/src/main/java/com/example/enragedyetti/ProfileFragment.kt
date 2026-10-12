package com.example.enragedyetti

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.enragedyetti.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set active user display name
        if (MainActivity.currentUsername.isNotEmpty()) {
            binding.tvProfileName.text = MainActivity.currentUsername
            binding.tvCurrentUsernameLabel.text = "Current: ${MainActivity.currentUsername}"
        }

        // Navigation / Tab Switching between sub-cards
        binding.btnMenuOrder.setOnClickListener {
            showCard(binding.cardOrders)
        }

        binding.btnMenuChangeUsername.setOnClickListener {
            showCard(binding.cardChangeUsername)
        }

        binding.btnMenuChangePassword.setOnClickListener {
            showCard(binding.cardChangePassword)
        }

        binding.btnMenuLogTicket.setOnClickListener {
            showCard(binding.cardLogTicket)
        }

        binding.btnMenuLogOut.setOnClickListener {
            MainActivity.isLoggedIn = false
            MainActivity.currentUsername = ""
            Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.SignInFragment)
        }

        // Action Handlers
        binding.btnSaveUsername.setOnClickListener {
            val newUsername = binding.etNewUsername.text.toString().trim()
            if (newUsername.isNotEmpty()) {
                MainActivity.currentUsername = newUsername
                binding.tvCurrentUsernameLabel.text = "Current: $newUsername"
                binding.tvProfileName.text = newUsername
                Toast.makeText(context, "Username updated to $newUsername", Toast.LENGTH_SHORT).show()
                binding.etNewUsername.text?.clear()
            } else {
                Toast.makeText(context, "Please enter a valid username", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSavePassword.setOnClickListener {
            val currentPass = binding.etCurrentPassword.text.toString()
            val newPass = binding.etNewPassword.text.toString()
            if (currentPass.isNotEmpty() && newPass.isNotEmpty()) {
                Toast.makeText(context, "Password updated successfully!", Toast.LENGTH_SHORT).show()
                binding.etCurrentPassword.text?.clear()
                binding.etNewPassword.text?.clear()
            } else {
                Toast.makeText(context, "Please fill in both password fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSubmitTicket.setOnClickListener {
            val subject = binding.etTicketSubject.text.toString().trim()
            if (subject.isNotEmpty()) {
                Toast.makeText(context, "Ticket submitted successfully!", Toast.LENGTH_SHORT).show()
                binding.etTicketSubject.text?.clear()
                binding.etTicketDetails.text?.clear()
            } else {
                Toast.makeText(context, "Please enter a subject for your ticket", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showCard(activeCard: View) {
        binding.cardOrders.visibility = View.GONE
        binding.cardChangeUsername.visibility = View.GONE
        binding.cardChangePassword.visibility = View.GONE
        binding.cardLogTicket.visibility = View.GONE

        activeCard.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}