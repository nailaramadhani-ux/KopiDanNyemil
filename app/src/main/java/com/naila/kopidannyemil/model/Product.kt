package com.naila.kopidannyemil.model

import java.io.Serializable

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val formattedPrice: String,
    val imageResId: Int
) : Serializable
