package com.example.enragedyetti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.enragedyetti.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddUltimate.setOnClickListener {
            CartManager.addItem(CartItem("Ultimate Gamer Pass", 499.0, R.drawable.memberships_pic))
            Toast.makeText(context, "Added Ultimate Gamer Pass to Cart!", Toast.LENGTH_SHORT).show()
        }
        binding.btnAddVip.setOnClickListener {
            CartManager.addItem(CartItem("VIP Gaming Experience", 799.0, R.drawable.memberships_pic))
            Toast.makeText(context, "Added VIP Gaming Experience to Cart!", Toast.LENGTH_SHORT).show()
        }
        binding.btnAddEsports.setOnClickListener {
            CartManager.addItem(CartItem("Esports Training Packages", 599.0, R.drawable.memberships_pic))
            Toast.makeText(context, "Added Esports Training Package to Cart!", Toast.LENGTH_SHORT).show()
        }
        binding.btnAddBirthday.setOnClickListener {
            CartManager.addItem(CartItem("Birthday Party Packages", 1299.0, R.drawable.memberships_pic))
            Toast.makeText(context, "Added Birthday Party Package to Cart!", Toast.LENGTH_SHORT).show()
        }

        binding.btnAddVr.setOnClickListener {
            CartManager.addItem(CartItem("Virtual Reality", 149.0, R.drawable.arena))
            Toast.makeText(context, "Added Virtual Reality to Cart!", Toast.LENGTH_SHORT).show()
        }
        binding.btnAddRacing.setOnClickListener {
            CartManager.addItem(CartItem("Racing Simulator Challenge", 179.0, R.drawable.arena))
            Toast.makeText(context, "Added Racing Simulator Challenge to Cart!", Toast.LENGTH_SHORT).show()
        }
        binding.btnAddEscape.setOnClickListener {
            CartManager.addItem(CartItem("Escape Room Challenge", 199.0, R.drawable.arena))
            Toast.makeText(context, "Added Escape Room Challenge to Cart!", Toast.LENGTH_SHORT).show()
        }

        binding.btnAddHoodie.setOnClickListener {
            CartManager.addItem(CartItem("Hoodie", 599.0, R.drawable.hoodie_nxtlvl))
            Toast.makeText(context, "Added NextLvl Hoodie to Cart!", Toast.LENGTH_SHORT).show()
        }
        binding.btnAddShirts.setOnClickListener {
            CartManager.addItem(CartItem("Merch Shirts", 249.0, R.drawable.nextlvl_merch_shirts))
            Toast.makeText(context, "Added Merch Shirt to Cart!", Toast.LENGTH_SHORT).show()
        }
        binding.btnAddKeyboard.setOnClickListener {
            CartManager.addItem(CartItem("Keyboard Set", 1299.0, R.drawable.keyboard_set))
            Toast.makeText(context, "Added Keyboard Set to Cart!", Toast.LENGTH_SHORT).show()
        }
        binding.btnAddPuffer.setOnClickListener {
            CartManager.addItem(CartItem("Enraged PufferJacket", 1799.0, R.drawable.enraged_pufferjacket))
            Toast.makeText(context, "Added Enraged PufferJacket to Cart!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}