package com.example.green_snack

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AllergiesInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_allergies_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val prevBtn = findViewById<Button>(R.id.prevButton)
        prevBtn.setOnClickListener {
            val intent = Intent(this, PersonalInfo::class.java)
            startActivity(intent)
        }

        val bolesti = findViewById<EditText>(R.id.bolesti)
        val alergije = findViewById<EditText>(R.id.alergije)

        val nextBtn = findViewById<Button>(R.id.nextButton)
        nextBtn.setOnClickListener {
            val intent = Intent(this, FoodPreferences::class.java).apply {
                putExtra("bolesti", bolesti.text.toString())
                putExtra("alergije", alergije.text.toString())
                putExtra("ime", intent.getStringExtra("ime"))
                putExtra("starosnaDob", intent.getIntExtra("starosnaDob", 0))
                putExtra("kilaza", intent.getDoubleExtra("kilaza", 0.0))
                putExtra("visina", intent.getDoubleExtra("visina", 0.0))
                putExtra("spol", intent.getStringExtra("spol"))
            }
            startActivity(intent)
        }
    }
}