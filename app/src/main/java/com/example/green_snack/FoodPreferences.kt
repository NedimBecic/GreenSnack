package com.example.green_snack

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FoodPreferences : AppCompatActivity() {
    private lateinit var categoryAdapter: CategoryAdapter // Declare the adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_food_preferences)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupUI()
    }

    private fun setupUI() {
        // Navigation button to go to the previous activity
        val prevBtn = findViewById<Button>(R.id.prevButton)
        prevBtn.setOnClickListener {
            val intent = Intent(this, AllergiesInfo::class.java)
            startActivity(intent)
        }

        // Setup RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 4)
        recyclerView.addItemDecoration(SpaceItemDecoration(16))

        val categoryItems = CategoryData.getCategories()
        categoryAdapter = CategoryAdapter(categoryItems) // Initialize the adapter
        recyclerView.adapter = categoryAdapter

        // Button for incrementing and decrementing the number of meals
        val increaseBtn = findViewById<ImageButton>(R.id.incrementButton)
        val decreaseBtn = findViewById<ImageButton>(R.id.decrementButton)
        val br_jela = findViewById<TextView>(R.id.numberDisplay)
        val budzet = findViewById<EditText>(R.id.budzet)

        var brojJelaUDanu = 0

        increaseBtn.setOnClickListener {
            brojJelaUDanu++
            br_jela.text = brojJelaUDanu.toString()
        }

        decreaseBtn.setOnClickListener {
            if (brojJelaUDanu > 0) {
                brojJelaUDanu--
                br_jela.text = brojJelaUDanu.toString()
            }
        }

        val nextBtn = findViewById<Button>(R.id.nextButton)
        nextBtn.setOnClickListener {
            val jelaUDanu = mutableListOf<String>()
            if (findViewById<SwitchCompat>(R.id.switchDorucak).isChecked) jelaUDanu.add("Doručak")
            if (findViewById<SwitchCompat>(R.id.switchRucak).isChecked) jelaUDanu.add("Ručak")
            if (findViewById<SwitchCompat>(R.id.switchVecera).isChecked) jelaUDanu.add("Večera")
            if (findViewById<SwitchCompat>(R.id.switchUzina).isChecked) jelaUDanu.add("Užina")

            val selectedCategories = categoryAdapter.getSelectedCategories()

            val intent = Intent(this, MainActivity::class.java).apply {
                val fullName = intent.getStringExtra("ime") ?: ""
                val (firstName, lastName) = splitFullName(fullName)
                val starosnaDob = intent.getIntExtra("starosnaDob", 0)
                val kilaza = intent.getDoubleExtra("kilaza", 0.0)
                val visina = intent.getDoubleExtra("visina", 0.0)
                val spol = intent.getStringExtra("spol") ?: ""
                val bolesti = intent.getStringExtra("bolesti")
                val alergije = intent.getStringExtra("alergije")

                val budzetValue = budzet.text.toString().toDoubleOrNull() ?: 0.0

                putExtra("firstName", firstName)
                putExtra("lastName", lastName)
                putExtra("spol", spol)
                putExtra("starosnaDob", starosnaDob)
                putExtra("kilaza", kilaza)
                putExtra("visina", visina)
                putExtra("bolesti", bolesti)
                putExtra("alergije", alergije)
                putExtra("brojJelaUDanu", brojJelaUDanu)
                putStringArrayListExtra("jelaUDanu", ArrayList(jelaUDanu))
                putStringArrayListExtra("selectedCategories", ArrayList(selectedCategories))
                putExtra("budzet",budzetValue)
            }
            startActivity(intent)
        }
    }

    private fun splitFullName(fullName: String): Pair<String, String> {
        val nameParts = fullName.split(" ")
        return if (nameParts.size >= 2) {
            Pair(nameParts[0], nameParts[1])
        } else {
            Pair("", "")
        }
    }
}
