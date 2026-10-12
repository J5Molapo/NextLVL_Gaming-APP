package com.example.enragedyetti

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.enragedyetti.databinding.ItemCartRowBinding
import java.util.Locale

class CartAdapter(
    private val items: MutableList<CartItem>,
    private val onItemRemoved: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(val binding: ItemCartRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvCartItemName.text = item.name
        holder.binding.tvCartItemPrice.text = String.format(Locale.US, "R%.2f", item.price)
        holder.binding.ivCartItemImage.setImageResource(item.imageRes)

        holder.binding.btnRemoveItem.setOnClickListener {
            val pos = holder.adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                CartManager.removeItemAt(pos)
                notifyDataSetChanged()
                onItemRemoved()
            }
        }
    }

    override fun getItemCount(): Int = items.size
}