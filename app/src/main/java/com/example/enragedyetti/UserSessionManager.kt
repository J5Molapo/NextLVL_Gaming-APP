package com.example.enragedyetti

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

data class UserAccount(
    var username: String = "",
    var email: String = "",
    var phone: String = "",
    var password: String = "",
    val orders: MutableList<OrderHistory> = mutableListOf(),
    val tickets: MutableList<SupportTicket> = mutableListOf(),
    val cards: MutableList<SavedCard> = mutableListOf()
)

data class OrderHistory(
    val id: String,
    val total: Double,
    val items: List<CartItem>,
    val timestamp: String
)

data class SupportTicket(
    val subject: String,
    val body: String,
    val timestamp: String
)

data class SavedCard(
    val number: String,
    val name: String
)

object UserSessionManager {
    var currentUser: UserAccount? = null
    val registeredAccounts = mutableListOf<UserAccount>()
    var tempSignUpContact: String = ""
    var tempSignUpMethod: String = "email"

    // Fresh initialization — NO prebuilt static profiles
    init {
        registeredAccounts.clear()
        currentUser = null
    }

    fun isLoggedIn(): Boolean = currentUser != null

    fun getCurrentUsername(): String = currentUser?.username ?: ""

    fun registerUser(username: String, pass: String): UserAccount {
        val user = UserAccount(
            username = username,
            password = pass,
            email = if (tempSignUpMethod == "email") tempSignUpContact else "",
            phone = if (tempSignUpMethod == "phone") tempSignUpContact else ""
        )
        registeredAccounts.add(user)
        currentUser = user
        return user
    }

    fun loginUser(identifier: String, pass: String): Pair<Boolean, String> {
        val match = registeredAccounts.find {
            (it.email.isNotEmpty() && it.email.equals(identifier, ignoreCase = true)) ||
            (it.phone.isNotEmpty() && it.phone.equals(identifier, ignoreCase = true)) ||
            (it.username.isNotEmpty() && it.username.equals(identifier, ignoreCase = true))
        }

        return if (match != null) {
            if (match.password == pass) {
                currentUser = match
                Pair(true, "Account found — redirecting...")
            } else {
                Pair(false, "Incorrect password.")
            }
        } else {
            Pair(false, "Account not found with $identifier. Please sign up.")
        }
    }

    fun logout() {
        currentUser = null
    }

    fun generateOrderId(): String {
        val randomNum = Random.nextInt(100000, 999999)
        return "ORD-2026-$randomNum"
    }

    fun getCurrentTimestamp(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy, HH:mm", Locale.US)
        return sdf.format(Date())
    }

    fun addOrder(order: OrderHistory) {
        val user = currentUser ?: UserAccount(username = "Guest").also {
            registeredAccounts.add(it)
            currentUser = it
        }
        user.orders.add(0, order)
    }

    fun addTicket(subject: String, body: String): SupportTicket {
        val ticket = SupportTicket(subject, body, getCurrentTimestamp())
        val user = currentUser ?: UserAccount(username = "Guest").also {
            registeredAccounts.add(it)
            currentUser = it
        }
        user.tickets.add(0, ticket)
        return ticket
    }

    fun addCard(number: String, name: String): SavedCard {
        val card = SavedCard(number, name)
        val user = currentUser ?: UserAccount(username = "Guest").also {
            registeredAccounts.add(it)
            currentUser = it
        }
        user.cards.add(card)
        return card
    }

    fun changePassword(oldPass: String, newPass: String): Pair<Boolean, String> {
        val user = currentUser ?: return Pair(false, "No active user session")
        if (user.password.isNotEmpty() && user.password != oldPass) {
            return Pair(false, "Current password does not match")
        }
        user.password = newPass
        return Pair(true, "Password changed successfully")
    }

    fun changeUsername(newUsername: String): Pair<Boolean, String> {
        val user = currentUser ?: return Pair(false, "No active user session")
        user.username = newUsername
        return Pair(true, "Username updated to $newUsername")
    }
}