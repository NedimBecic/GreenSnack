package com.example.green_snack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

class Home : Fragment() {
    private val mealsData = mapOf(
        "Doručak" to "Recipe for Doručak: Eggs, bread, and orange juice.",
        "Ručak" to "Recipe for Ručak: Grilled chicken, rice, and salad.",
        "Večera" to "Recipe for Večera: Pasta, garlic bread, and dessert."
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val dorucakView = view.findViewById<View>(R.id.dorucak)
        val rucakView = view.findViewById<View>(R.id.rucak)
        val veceraView = view.findViewById<View>(R.id.vecera)

        dorucakView.setOnClickListener {
            showMealBottomSheet("Doručak", mealsData["Doručak"] ?: "No recipe available.")
        }

        rucakView.setOnClickListener {
            showMealBottomSheet("Ručak", mealsData["Ručak"] ?: "No recipe available.")
        }

        veceraView.setOnClickListener {
            showMealBottomSheet("Večera", mealsData["Večera"] ?: "No recipe available.")
        }

        return view
    }

    private fun showMealBottomSheet(title: String, recipe: String) {
        val bottomSheet = MealBottomSheetFragment.newInstance(title, recipe)
        bottomSheet.show(parentFragmentManager, bottomSheet.tag)
    }

}