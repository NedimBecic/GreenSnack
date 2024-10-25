package com.example.green_snack

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PersonalInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_personal_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val daljeBtn = findViewById<Button>(R.id.nextButton)
        daljeBtn.setOnClickListener {
            val intent = Intent(this, AllergiesInfo::class.java)
            startActivity(intent)
        }
        val prevBtn = findViewById<Button>(R.id.prevButton)
        prevBtn.setOnClickListener {
            val intent = Intent(this, Intro::class.java)
            startActivity(intent)
        }
    }
}