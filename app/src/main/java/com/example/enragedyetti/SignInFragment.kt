package com.example.enragedyetti

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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
            val contact = binding.etEmail.text.toString().trim()
            val method = binding.etContactMethod.text.toString().trim().lowercase()

            if (contact.isEmpty()) {
                binding.etEmail.error = "Please enter your contact details"
                return@setOnClickListener
            }

            if (method.contains("email") && !Patterns.EMAIL_ADDRESS.matcher(contact).matches()) {
                binding.etEmail.error = "Please enter a valid email address (e.g. name@example.com)"
                return@setOnClickListener
            }

            if (method.contains("phone") && contact.length < 10) {
                binding.etEmail.error = "Please enter a valid phone number (at least 10 digits)"
                return@setOnClickListener
            }

            binding.etEmail.error = null
            UserSessionManager.tempSignUpContact = contact
            UserSessionManager.tempSignUpMethod = if (method.contains("phone")) "phone" else "email"

            // Advance to Choose Username
            binding.cardSignIn.visibility = View.GONE
            binding.cardChooseUsername.visibility = View.VISIBLE
        }

        binding.btnLogin.setOnClickListener {
            val identifier = binding.etLoginEmail.text.toString().trim()
            val pass = binding.etLoginPassword.text.toString().trim()

            var isValid = true

            if (identifier.isEmpty()) {
                binding.etLoginEmail.error = "Email or phone number is required"
                isValid = false
            } else if (identifier.contains("@") && !Patterns.EMAIL_ADDRESS.matcher(identifier).matches()) {
                binding.etLoginEmail.error = "Please enter a valid email address"
                isValid = false
            } else {
                binding.etLoginEmail.error = null
            }

            if (pass.isEmpty()) {
                binding.etLoginPassword.error = "Password is required"
                isValid = false
            } else if (pass.length < 6) {
                binding.etLoginPassword.error = "Password must be at least 6 characters long"
                isValid = false
            } else {
                binding.etLoginPassword.error = null
            }

            if (isValid) {
                val (success, message) = UserSessionManager.loginUser(identifier, pass)
                if (success) {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.ProfileFragment)
                } else {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.btnCreateAccount.setOnClickListener {
            val username = binding.etChooseUsername.text.toString().trim()
            val pass = binding.etChoosePassword.text.toString().trim()

            var isValid = true

            if (username.isEmpty()) {
                binding.etChooseUsername.error = "Username is required"
                isValid = false
            } else if (username.length < 3) {
                binding.etChooseUsername.error = "Username must be at least 3 characters long"
                isValid = false
            } else if (username.contains(" ")) {
                binding.etChooseUsername.error = "Username cannot contain spaces"
                isValid = false
            } else {
                binding.etChooseUsername.error = null
            }

            if (pass.isEmpty()) {
                binding.etChoosePassword.error = "Password is required"
                isValid = false
            } else if (pass.length < 6) {
                binding.etChoosePassword.error = "Password must be at least 6 characters long"
                isValid = false
            } else {
                binding.etChoosePassword.error = null
            }

            if (isValid) {
                UserSessionManager.registerUser(username, pass)
                Toast.makeText(context, "Account created successfully for $username!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.ProfileFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}