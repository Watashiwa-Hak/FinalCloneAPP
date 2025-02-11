package com.hakudesu.finalcloneapp.models

data class CartItem(
    val id: Int,
    val name: String,
    val price: Double,
    val imageRes: Int,
    var quantity: Int = 0
)