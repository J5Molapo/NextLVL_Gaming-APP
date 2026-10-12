package com.example.enragedyetti

data class CartItem(
    val name: String,
    val price: Double,
    val imageRes: Int = R.drawable.nextlvl
)

object CartManager {
    val items = mutableListOf<CartItem>()

    fun addItem(item: CartItem) {
        items.add(item)
    }

    fun removeItemAt(index: Int) {
        if (index in items.indices) {
            items.removeAt(index)
        }
    }

    fun clear() {
        items.clear()
    }

    fun getSubtotal(): Double {
        return items.sumOf { it.price }
    }

    fun getDiscount(): Double {
        val subtotal = getSubtotal()
        val percent = when {
            items.size > 3 -> 0.15
            items.size == 3 -> 0.10
            items.size == 2 -> 0.05
            else -> 0.0
        }
        return subtotal * percent
    }

    fun getDiscountPercent(): Int {
        return when {
            items.size > 3 -> 15
            items.size == 3 -> 10
            items.size == 2 -> 5
            else -> 0
        }
    }

    fun getFees(): Double {
        val subtotal = getSubtotal()
        return if (items.isEmpty()) 0.0 else (20.0 + subtotal * 0.025)
    }

    fun getGrandTotal(): Double {
        val total = getSubtotal() - getDiscount() + getFees()
        return if (total < 0) 0.0 else total
    }
}