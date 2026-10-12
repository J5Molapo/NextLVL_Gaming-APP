package com.example.enragedyetti

import android.graphics.Color
import android.os.Bundle
import android.view.View
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

        // Navigation Header Tab Clicks
        binding.btnHeaderLogo.setOnClickListener {
            navController.navigate(R.id.HomeFragment)
        }
        binding.tabHome.setOnClickListener {
            navController.navigate(R.id.HomeFragment)
        }
        binding.tabCart.setOnClickListener {
            navController.navigate(R.id.CartFragment)
        }
        binding.tabOrders.setOnClickListener {
            if (isLoggedIn) {
                navController.navigate(R.id.ProfileFragment)
            } else {
                navController.navigate(R.id.SignInFragment)
            }
        }
        binding.tabProfile.setOnClickListener {
            if (isLoggedIn) {
                navController.navigate(R.id.ProfileFragment)
            } else {
                navController.navigate(R.id.SignInFragment)
            }
        }

        // Highlight active tab matching user screenshot
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Reset all tabs to unselected state
            resetTabs()

            when (destination.id) {
                R.id.HomeFragment -> {
                    setActiveTab(binding.ivTabHomeIcon, binding.tvTabHomeText)
                }
                R.id.CartFragment, R.id.CheckoutFragment, R.id.PaymentSuccessfulFragment -> {
                    setActiveTab(binding.ivTabCartIcon, binding.tvTabCartText)
                }
                R.id.ProfileFragment -> {
                    setActiveTab(binding.ivTabOrdersIcon, binding.tvTabOrdersText)
                }
                R.id.SignInFragment -> {
                    setActiveTab(binding.ivTabProfileIcon, binding.tvTabProfileText)
                }
            }
        }
    }

    private fun resetTabs() {
        binding.ivTabHomeIcon.setColorFilter(Color.parseColor("#80FFFFFF"))
        binding.tvTabHomeText.visibility = View.GONE

        binding.ivTabCartIcon.setColorFilter(Color.parseColor("#80FFFFFF"))
        binding.tvTabCartText.visibility = View.GONE

        binding.ivTabOrdersIcon.setColorFilter(Color.parseColor("#80FFFFFF"))
        binding.tvTabOrdersText.visibility = View.GONE

        binding.ivTabProfileIcon.setColorFilter(Color.parseColor("#80FFFFFF"))
        binding.tvTabProfileText.visibility = View.GONE
        binding.tvTabProfileText.text = if (isLoggedIn) "Account" else "Sign In"
    }

    private fun setActiveTab(iconView: android.widget.ImageView, textView: android.widget.TextView) {
        iconView.setColorFilter(Color.parseColor("#00FF66"))
        textView.visibility = View.VISIBLE
        textView.setTextColor(Color.parseColor("#00FF66"))
    }
}