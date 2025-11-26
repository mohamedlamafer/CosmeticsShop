package com.example.cosmeticsapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        supportActionBar?.hide()


        val logo = findViewById<ImageView>(R.id.logo)
        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        logo.startAnimation(anim)


        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, CreateAccountActivity::class.java))
            finish()
        }, 3000)
    }
}
