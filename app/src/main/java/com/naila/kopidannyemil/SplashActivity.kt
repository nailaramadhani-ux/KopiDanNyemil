package com.naila.kopidannyemil

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.naila.kopidannyemil.databinding.ActivitySplashBinding
import com.naila.kopidannyemil.pref.UserPreferences

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreferences = UserPreferences(this)

        binding.btnStartNow.setOnClickListener {
            if (userPreferences.isLoggedIn()) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, SignUpChoiceActivity::class.java)
                startActivity(intent)
            }
            finish()
        }
    }
}
