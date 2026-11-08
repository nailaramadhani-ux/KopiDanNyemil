package com.naila.kopidannyemil.model

import com.naila.kopidannyemil.R

object ProductData {
    fun getProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                name = "Kopi Susu Gula Aren",
                description = "Espresso, susu, gula aren",
                price = 22000,
                formattedPrice = "Rp 22.000",
                imageResId = R.drawable.img_kopi_susu_gula_aren
            ),
            Product(
                id = 2,
                name = "Donat Cokelat",
                description = "Donat empuk, glaze cokelat",
                price = 12000,
                formattedPrice = "Rp 12.000",
                imageResId = R.drawable.img_donat_cokelat
            ),
            Product(
                id = 3,
                name = "Sourdough Loaf",
                description = "Roti fermentasi alami",
                price = 35000,
                formattedPrice = "Rp 35.000",
                imageResId = R.drawable.img_sourdough_loaf
            ),
            Product(
                id = 4,
                name = "Cappuccino",
                description = "Espresso, foam susu lembut",
                price = 25000,
                formattedPrice = "Rp 25.000",
                imageResId = R.drawable.img_cappuccino
            ),
            Product(
                id = 5,
                name = "Matcha Latte",
                description = "Matcha jepang, susu segar",
                price = 27000,
                formattedPrice = "Rp 27.000",
                imageResId = R.drawable.img_matcha_latte
            )
        )
    }
}
