package com.hakudesu.finalcloneapp.viewmodels
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.ViewModel
import com.hakudesu.finalcloneapp.models.CartItem
import com.hakudesu.finalcloneapp.R
import com.hakudesu.finalcloneapp.models.CartModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CartViewModel : ViewModel() {

    private val _cartState = MutableStateFlow(CartModel(emptyList(), 0.0))
    val cartState: StateFlow<CartModel> = _cartState

    fun updateUserInfo(fullName: String, tel: String, address: String) {
        _cartState.value = _cartState.value.copy(fullName = fullName, tel = tel, address = address)
    }

    private val _cartItems = MutableStateFlow(
        listOf(
            CartItem(1, "Hawaiian Pizza", 2.99, R.drawable.pizzafood),
            CartItem(2, "Tuna Sushi", 4.99, R.drawable.sushifood),
            CartItem(3, "Hamburger", 5.99, R.drawable.burgerfood)
        )
    )

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice = _totalPrice.asStateFlow()




    val cartItems: StateFlow<List<CartItem>> = _cartItems

    fun updateQuantity(itemId: Int, newQuantity: Int) {
        if (newQuantity < 0) return
        println("Updating item $itemId with quantity $newQuantity") // Debugging
        _cartItems.update { items ->
            items.map { item ->
                if (item.id == itemId) {
                    item.copy(quantity = newQuantity)
                } else {
                    item
                }
            }
        }
        calculateTotalPrice()
    }
    private fun calculateTotalPrice() {
        _totalPrice.value = _cartItems.value.sumOf { it.price * it.quantity }
    }
}
