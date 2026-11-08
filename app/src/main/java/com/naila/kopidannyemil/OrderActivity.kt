package com.naila.kopidannyemil

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.naila.kopidannyemil.databinding.ActivityOrderBinding
import com.naila.kopidannyemil.model.Product
import com.naila.kopidannyemil.model.ProductData
import com.naila.kopidannyemil.pref.UserPreferences

class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding
    private lateinit var userPreferences: UserPreferences
    private var selectedProduct: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreferences = UserPreferences(this)

        selectedProduct = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(HomeActivity.EXTRA_PRODUCT, Product::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(HomeActivity.EXTRA_PRODUCT) as? Product
        }

        if (selectedProduct == null) {
            selectedProduct = ProductData.getProducts().first()
        }

        displayProductInfo()
        prefillUserData()

        binding.btnOrder.setOnClickListener {
            val fullName = binding.etFullName.text?.toString()?.trim() ?: ""
            val address = binding.etAddress.text?.toString()?.trim() ?: ""
            val locationDetail = binding.etLocationDetail.text?.toString()?.trim() ?: ""

            if (fullName.isEmpty()) {
                binding.tilFullName.error = "Nama lengkap tidak boleh kosong"
                return@setOnClickListener
            } else {
                binding.tilFullName.error = null
            }

            if (address.isEmpty()) {
                binding.tilAddress.error = "Alamat tidak boleh kosong"
                return@setOnClickListener
            } else {
                binding.tilAddress.error = null
            }

            if (locationDetail.isEmpty()) {
                binding.tilLocationDetail.error = "Detail lokasi tidak boleh kosong"
                return@setOnClickListener
            } else {
                binding.tilLocationDetail.error = null
            }

            val intent = Intent(this, ConfirmationActivity::class.java).apply {
                putExtra(EXTRA_USER_NAME, fullName)
                putExtra(HomeActivity.EXTRA_PRODUCT, selectedProduct)
            }
            startActivity(intent)
            finish()
        }

        setupBottomNavigation()
    }

    private fun displayProductInfo() {
        selectedProduct?.let { product ->
            binding.imgProduct.setImageResource(product.imageResId)
            binding.tvProductName.text = product.name
            binding.tvProductPrice.text = product.formattedPrice
        }
    }

    private fun prefillUserData() {
        val savedName = userPreferences.getFullName()
        if (savedName.isNotEmpty() && savedName != "Pengguna") {
            binding.etFullName.setText(savedName)
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNav.btnNavOrder.setBackgroundColor(getColor(R.color.coffee_primary))
        binding.bottomNav.btnNavOrder.setTextColor(getColor(R.color.white))

        binding.bottomNav.btnNavHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(intent)
            finish()
        }

        binding.bottomNav.btnNavProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    companion object {
        const val EXTRA_USER_NAME = "extra_user_name"
    }
}
