package com.example.enragedyetti

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import com.example.enragedyetti.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // 1. Splash Screen Phase-In Animation
        startSplashPhaseInAnimation()

        // 2. Hamburger Menu Click Listener
        binding.btnHamburgerMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.btnHeaderLogo.setOnClickListener {
            navController.navigate(R.id.HomeFragment)
        }

        binding.btnNotification.setOnClickListener {
            navController.navigate(R.id.EnquiryFragment)
        }

        // 3. Bottom Navigation Bar Clicks (Matching Behance UI Layout)
        binding.tabHome.setOnClickListener {
            navController.navigate(R.id.HomeFragment)
        }
        binding.tabProducts.setOnClickListener {
            navController.navigate(R.id.ProductsFragment)
        }
        binding.tabCart.setOnClickListener {
            navController.navigate(R.id.CartFragment)
        }
        binding.tabProfile.setOnClickListener {
            if (UserSessionManager.isLoggedIn()) {
                navController.navigate(R.id.ProfileFragment)
            } else {
                navController.navigate(R.id.SignInFragment)
            }
        }

        // 4. Hamburger Navigation Drawer Item Clicks
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            val destinationId = menuItem.itemId
            binding.drawerLayout.closeDrawer(GravityCompat.START)

            if (destinationId == R.id.ProfileFragment && !UserSessionManager.isLoggedIn()) {
                navController.navigate(R.id.SignInFragment)
            } else {
                try {
                    navController.navigate(destinationId)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            true
        }

        // Highlight active bottom nav tab
        navController.addOnDestinationChangedListener { _, destination, _ ->
            resetBottomTabs()

            when (destination.id) {
                R.id.HomeFragment -> {
                    binding.ivTabHomeIcon.setColorFilter(Color.parseColor("#00FF66"))
                }
                R.id.ProductsFragment -> {
                    binding.ivTabProductsIcon.setColorFilter(Color.parseColor("#00FF66"))
                }
                R.id.CartFragment, R.id.CheckoutFragment, R.id.PaymentSuccessfulFragment -> {
                    binding.ivTabCartIcon.setColorFilter(Color.parseColor("#00FF66"))
                }
                R.id.SignInFragment, R.id.ProfileFragment -> {
                    binding.ivTabProfileIcon.setColorFilter(Color.parseColor("#00FF66"))
                }
            }
        }

        // Handle Back Button for Drawer
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    private fun resetBottomTabs() {
        binding.ivTabHomeIcon.setColorFilter(Color.parseColor("#80FFFFFF"))
        binding.ivTabProductsIcon.setColorFilter(Color.parseColor("#80FFFFFF"))
        binding.ivTabCartIcon.setColorFilter(Color.parseColor("#80FFFFFF"))
        binding.ivTabProfileIcon.setColorFilter(Color.parseColor("#80FFFFFF"))
    }

    private fun startSplashPhaseInAnimation() {
        // Initial state for phase-in
        binding.splashLogo.alpha = 0f
        binding.splashLogo.scaleX = 0.85f
        binding.splashLogo.scaleY = 0.85f

        binding.splashText.alpha = 0f

        // Phase in standalone logo
        binding.splashLogo.animate()
            .alpha(1f)
            .scaleX(1.0f)
            .scaleY(1.0f)
            .setDuration(1200)
            .start()

        // Phase in text slightly after
        binding.splashText.animate()
            .alpha(1f)
            .setStartDelay(300)
            .setDuration(1000)
            .start()

        // Fade out splash overlay after 2.4 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            binding.splashOverlay.animate()
                .alpha(0f)
                .setDuration(600)
                .withEndAction {
                    binding.splashOverlay.visibility = View.GONE
                }
                .start()
        }, 2400)
    }
}