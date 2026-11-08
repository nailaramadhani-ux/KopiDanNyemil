package com.naila.kopidannyemil

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.naila.kopidannyemil.adapter.ProductAdapter
import com.naila.kopidannyemil.databinding.ActivityHomeBinding
import com.naila.kopidannyemil.model.ProductData
import com.naila.kopidannyemil.pref.UserPreferences

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreferences = UserPreferences(this)

        val name = userPreferences.getFullName()
        binding.tvGreeting.text = "Halo $name,"

        setupRecyclerView()
        setupBottomNavigation()
    }

    override fun onResume() {
        super.onResume()
        val name = userPreferences.getFullName()
        binding.tvGreeting.text = "Halo $name,"
    }

    private fun setupRecyclerView() {
        val products = ProductData.getProducts()
        val adapter = ProductAdapter(products) { selectedProduct ->
            val intent = Intent(this, OrderActivity::class.java).apply {
                putExtra(EXTRA_PRODUCT, selectedProduct)
            }
            startActivity(intent)
        }
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.adapter = adapter
    }

    private fun setupBottomNavigation() {
        binding.bottomNav.btnNavHome.setBackgroundColor(getColor(R.color.coffee_primary))
        binding.bottomNav.btnNavHome.setTextColor(getColor(R.color.white))

        binding.bottomNav.btnNavOrder.setOnClickListener {
            val defaultProduct = ProductData.getProducts().first()
            val intent = Intent(this, OrderActivity::class.java).apply {
                putExtra(EXTRA_PRODUCT, defaultProduct)
            }
            startActivity(intent)
        }

        binding.bottomNav.btnNavProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_PRODUCT = "extra_product"
    }
}
