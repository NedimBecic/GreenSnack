package com.example.green_snack

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.green_snack.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Retrieve user data from Intent
        val firstName = intent.getStringExtra("firstName") ?: ""
        val lastName = intent.getStringExtra("lastName") ?: ""
        val starosnaDob = intent.getIntExtra("starosnaDob", 0)
        val kilaza = intent.getDoubleExtra("kilaza", 0.0)
        val visina = intent.getDoubleExtra("visina", 0.0)
        val spol = intent.getStringExtra("spol") ?: ""
        val bolesti = intent.getStringExtra("bolesti")
        val alergije = intent.getStringExtra("alergije")
        val brojJelaUDanu = intent.getIntExtra("brojJelaUDanu", 0)
        val jelaUDanu = intent.getStringArrayListExtra("jelaUDanu") ?: arrayListOf()
        val selectedCategories = intent.getStringArrayListExtra("selectedCategories") ?: arrayListOf()
        val budzet = intent.getDoubleExtra("budzet", 0.0)

        // Create Bundle to pass parameters to Home fragment
        val bundle = Bundle().apply {
            putString("firstName", firstName)
            putString("lastName", lastName)
            putString("spol", spol)
            putInt("starosnaDob", starosnaDob)
            putDouble("kilaza", kilaza)
            putDouble("visina", visina)
            putString("bolesti", bolesti)
            putString("alergije", alergije)
            putInt("brojJelaUDanu", brojJelaUDanu)
            putStringArrayList("jelaUDanu", jelaUDanu)
            putStringArrayList("selectedCategories", selectedCategories)
            putDouble("budzet", budzet)
        }

        // Create Home fragment and set the arguments
        val homeFragment = Home().apply {
            arguments = bundle
        }
        replaceFragment(homeFragment)

       binding.bottomNavigationView.setOnItemSelectedListener {
           when(it.itemId){
               R.id.home -> {
                   val homeFragment2 = Home().apply {
                       arguments = bundle
                   }
                   replaceFragment(homeFragment2)
               }
               R.id.plan -> {
                   val weekFragment = WeekPlan().apply {
                       arguments = bundle
                   }
                   replaceFragment(weekFragment)
               }
               R.id.stats -> {
                   val statsFragment = Stats().apply {
                       arguments = bundle
                   }
                   replaceFragment(statsFragment)
               }
              R.id.profile -> {
                  val profileFragment = Profile().apply {
                      arguments = bundle
                  }
                  replaceFragment(profileFragment)
              }
              else ->{
               }
          }
          true
      }

    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}