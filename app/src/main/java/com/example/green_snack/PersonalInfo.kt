package com.example.green_snack

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
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

        val prevBtn = findViewById<Button>(R.id.prevButton)
        prevBtn.setOnClickListener {
            val intent = Intent(this, Intro::class.java)
            startActivity(intent)
        }
        val spinner = findViewById<Spinner>(R.id.genderSpinner)
        val genderOptions = listOf("Odaberi spol", "Muški", "Ženski", "Ostali")

        val adapter = ArrayAdapter(this, R.layout.spinner_item, genderOptions)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setSelection(0)

        val imeprezime = findViewById<EditText>(R.id.imeprezime)
        val starosnaDob = findViewById<EditText>(R.id.starosnadob)
        val kilaza = findViewById<EditText>(R.id.kilaza)
        val visina = findViewById<EditText>(R.id.visina)

        daljeBtn.setOnClickListener {
            val intent = Intent(this, AllergiesInfo::class.java).apply {
                putExtra("ime", imeprezime.text.toString())
                putExtra("starosnaDob", starosnaDob.text.toString().toInt())
                putExtra("kilaza", kilaza.text.toString().toDouble())
                putExtra("visina", visina.text.toString().toDouble())
                putExtra("spol", spinner.selectedItem.toString())
            }
            startActivity(intent)
        }


    }
}