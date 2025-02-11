package com.hakudesu.finalcloneapp.models

data class CartModel(
    val items: List<CartItem>,
    val totalPrice: Double,
    var fullName: String = "",
    var tel: String = "",
    var address: String = ""
)
