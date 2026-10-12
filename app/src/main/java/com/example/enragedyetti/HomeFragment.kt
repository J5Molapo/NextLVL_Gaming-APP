package com.example.enragedyetti

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.enragedyetti.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Category Filter Pill Clicks
        val pills = listOf(binding.pillAll, binding.pillPackages, binding.pillExperiences, binding.pillMerch, binding.pillEsports)
        pills.forEach { pill ->
            pill.setOnClickListener {
                pills.forEach {
                    it.isSelected = false
                    it.setTextColor(android.graphics.Color.WHITE)
                }
                pill.isSelected = true
                pill.setTextColor(android.graphics.Color.BLACK)
            }
        }

        binding.btnViewProducts.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_ProductsFragment)
        }

        binding.btnLearnMore.setOnClickListener {
            findNavController().navigate(R.id.AboutFragment)
        }

        binding.btnSeeAllProducts.setOnClickListener {
            findNavController().navigate(R.id.ProductsFragment)
        }

        binding.btnHeroViewPass.setOnClickListener {
            findNavController().navigate(R.id.ProductsFragment)
        }

        binding.btnHeroViewArena.setOnClickListener {
            findNavController().navigate(R.id.ProductsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}