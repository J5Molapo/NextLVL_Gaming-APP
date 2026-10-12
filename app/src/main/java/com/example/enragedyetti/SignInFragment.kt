package com.example.enragedyetti

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.enragedyetti.databinding.FragmentSigninBinding

class SignInFragment : Fragment() {
    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Switch to "Choose a Username" (Section 4)
        binding.btnGotoChooseUsername.setOnClickListener {
            binding.cardSignIn.visibility = View.GONE
            binding.cardChooseUsername.visibility = View.VISIBLE
        }

        binding.btnBackToSignIn.setOnClickListener {
            binding.cardChooseUsername.visibility = View.GONE
            binding.cardSignIn.visibility = View.VISIBLE
        }

        binding.btnContinueSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            if (email.isNotEmpty()) {
                // Advance to Choose Username
                binding.cardSignIn.visibility = View.GONE
                binding.cardChooseUsername.visibility = View.VISIBLE
            } else {
                Toast.makeText(context, "Please enter your email address", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString().trim()
            val pass = binding.etLoginPassword.text.toString().trim()
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                val username = if (email.contains("@")) email.substringBefore("@") else email
                MainActivity.isLoggedIn = true
                MainActivity.currentUsername = username
                Toast.makeText(context, "Welcome back, $username!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.ProfileFragment)
            } else {
                Toast.makeText(context, "Please enter email/phone and password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCreateAccount.setOnClickListener {
            val username = binding.etChooseUsername.text.toString().trim()
            val pass = binding.etChoosePassword.text.toString().trim()
            if (username.isNotEmpty() && pass.isNotEmpty()) {
                MainActivity.isLoggedIn = true
                MainActivity.currentUsername = username
                Toast.makeText(context, "Account created successfully for $username!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.ProfileFragment)
            } else {
                Toast.makeText(context, "Please choose a username and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}