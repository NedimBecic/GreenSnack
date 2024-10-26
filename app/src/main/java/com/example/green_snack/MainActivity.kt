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
     replaceFragment(Home())

       binding.bottomNavigationView.setOnItemSelectedListener {
           when(it.itemId){
               R.id.home -> replaceFragment(Home())
               R.id.plan -> replaceFragment(WeekPlan())
               R.id.stats -> replaceFragment(Stats())
              R.id.profile -> replaceFragment(Profile())
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