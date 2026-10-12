package com.example.enragedyetti

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.enragedyetti.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    
    companion object {
        var isLoggedIn: Boolean = false
        var currentUsername: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Set click listeners for top navigation buttons
        binding.btnHeaderLogo.setOnClickListener {
            navController.navigate(R.id.HomeFragment)
        }
        binding.btnNavHome.setOnClickListener {
            navController.navigate(R.id.HomeFragment)
        }
        binding.btnNavAbout.setOnClickListener {
            navController.navigate(R.id.AboutFragment)
        }
        binding.btnNavProducts.setOnClickListener {
            navController.navigate(R.id.ProductsFragment)
        }
        binding.btnNavContact.setOnClickListener {
            navController.navigate(R.id.ContactFragment)
        }
        binding.btnNavEnquiry.setOnClickListener {
            navController.navigate(R.id.EnquiryFragment)
        }
        binding.btnNavProfile.setOnClickListener {
            if (isLoggedIn) {
                navController.navigate(R.id.ProfileFragment)
            } else {
                navController.navigate(R.id.SignInFragment)
            }
        }
        binding.btnNavCart.setOnClickListener {
            navController.navigate(R.id.CartFragment)
        }

        // Highlight active navigation button state and update account label dynamically
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Update account button label based on login status
            if (isLoggedIn && currentUsername.isNotEmpty()) {
                binding.btnNavProfile.text = "Yetti: $currentUsername"
            } else {
                binding.btnNavProfile.text = "Sign In"
            }

            binding.btnNavHome.isSelected = (destination.id == R.id.HomeFragment)
            binding.btnNavAbout.isSelected = (destination.id == R.id.AboutFragment)
            binding.btnNavProducts.isSelected = (destination.id == R.id.ProductsFragment)
            binding.btnNavContact.isSelected = (destination.id == R.id.ContactFragment)
            binding.btnNavEnquiry.isSelected = (destination.id == R.id.EnquiryFragment)
            binding.btnNavProfile.isSelected = (destination.id == R.id.SignInFragment || destination.id == R.id.ProfileFragment)
            binding.btnNavCart.isSelected = (destination.id == R.id.CartFragment || destination.id == R.id.CheckoutFragment || destination.id == R.id.PaymentSuccessfulFragment)
        }
    }
}