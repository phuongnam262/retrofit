package com.example.retrofit.model

import java.io.Serializable

data class Product(
    val id: String ? =null,
    val title: String ? =null,
    val price: String ? =null,
    val image: String ? =null
): Serializable