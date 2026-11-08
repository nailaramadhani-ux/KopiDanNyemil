package com.naila.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.naila.kopidannyemil.databinding.ActivityProfileBinding
import com.naila.kopidannyemil.model.ProductData
import com.naila.kopidannyemil.pref.UserPreferences

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreferences = UserPreferences(this)

        val currentName = userPreferences.getFullName()
        binding.tvGreeting.text = "Halo $currentName,"
        binding.etName.setText(currentName)

        binding.btnSave.setOnClickListener {
            val newName = binding.etName.text?.toString()?.trim() ?: ""
            if (newName.isEmpty()) {
                binding.tilName.error = "Nama tidak boleh kosong"
            } else {
                binding.tilName.error = null
                userPreferences.updateFullName(newName)
                binding.tvGreeting.text = "Halo $newName,"
                Toast.makeText(this, "Profil berhasil diperbarui!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLogout.setOnClickListener {
            userPreferences.logout()
            Toast.makeText(this, "Anda telah keluar", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            finish()
        }

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomNav.btnNavProfile.setBackgroundColor(getColor(R.color.coffee_primary))
        binding.bottomNav.btnNavProfile.setTextColor(getColor(R.color.white))

        binding.bottomNav.btnNavHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(intent)
            finish()
        }

        binding.bottomNav.btnNavOrder.setOnClickListener {
            val defaultProduct = ProductData.getProducts().first()
            val intent = Intent(this, OrderActivity::class.java).apply {
                putExtra(HomeActivity.EXTRA_PRODUCT, defaultProduct)
            }
            startActivity(intent)
        }
    }
}
