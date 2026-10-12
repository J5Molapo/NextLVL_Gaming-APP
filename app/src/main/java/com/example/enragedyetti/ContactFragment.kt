package com.example.enragedyetti

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.enragedyetti.databinding.FragmentContactBinding

class ContactFragment : Fragment() {
    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Launch Phone Dialer
        binding.btnContactPhone.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+27123456789"))
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, "Unable to open phone app", Toast.LENGTH_SHORT).show()
            }
        }

        // Launch Email App
        binding.btnContactEmail.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:hello@nextlvlgaming.co.za")
                    putExtra(Intent.EXTRA_SUBJECT, "NextLVL Gaming Inquiry")
                }
                startActivity(Intent.createChooser(intent, "Send Email via..."))
            } catch (e: Exception) {
                Toast.makeText(context, "Unable to open email app", Toast.LENGTH_SHORT).show()
            }
        }

        // Launch Maps App
        binding.btnContactAddress.setOnClickListener {
            try {
                val geoUri = Uri.parse("geo:0,0?q=42+Neon+Lane+Sandton+Johannesburg+South+Africa")
                val intent = Intent(Intent.ACTION_VIEW, geoUri)
                startActivity(Intent.createChooser(intent, "Open HQ Location in..."))
            } catch (e: Exception) {
                Toast.makeText(context, "Unable to open maps app", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}