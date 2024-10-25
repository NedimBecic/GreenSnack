package com.example.green_snack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction

class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val dorucakBtn = view.findViewById<Button>(R.id.dorucak)
        val rucakBtn = view.findViewById<Button>(R.id.rucak)
        val veceraBtn = view.findViewById<Button>(R.id.vecera)
        val piceBtn = view.findViewById<Button>(R.id.pice)
        val ostaloBtn = view.findViewById<Button>(R.id.ostalo)

        val buttons = listOf(dorucakBtn, rucakBtn, veceraBtn, piceBtn, ostaloBtn)
        for (button in buttons) {
            button.setOnClickListener {
                val detailFragment = DetailFragment()
                val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.frame_layout, detailFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

        return view
    }
}