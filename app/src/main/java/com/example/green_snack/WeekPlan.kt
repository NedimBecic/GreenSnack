package com.example.green_snack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.green_snack.databinding.FragmentWeekPlanBinding

class WeekPlan : Fragment() {

    // Declare the constants here
    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentWeekPlanBinding
    private lateinit var adapter: WeekPlanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using View Binding
        binding = FragmentWeekPlanBinding.inflate(inflater, container, false)

        // Initialize RecyclerView
        val weekPlanData = generateMockWeekPlans() // Use mock data or fetch real data
        adapter = WeekPlanAdapter(weekPlanData)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    private fun generateMockWeekPlans(): List<MealPlan> {
        return listOf(
            MealPlan(
                dayTitle = "Ponedjeljak",
                meals = listOf(
                    Meal("Doručak", "8:00 AM - 5€ uštede", R.drawable.breakfast),
                    Meal("Ručak", "12:30 PM - 10€ uštede", R.drawable.lunch),
                    Meal("Večera", "7:00 PM - 8€ uštede", R.drawable.dinnermain)
                )
            ),
            MealPlan(
                dayTitle = "Utorak",
                meals = listOf(
                    Meal("Doručak", "8:00 AM - 1€ uštede", R.drawable.breakfast),
                    Meal("Ručak", "12:30 PM - 20€ uštede", R.drawable.lunch),
                    Meal("Večera", "7:00 PM - 6€ uštede", R.drawable.dinnermain)
                )
            ),
            MealPlan(
                dayTitle = "Srijeda",
                meals = listOf(
                    Meal("Doručak", "8:00 AM - 2€ uštede", R.drawable.breakfast),
                    Meal("Ručak", "12:30 PM - 12€ uštede", R.drawable.lunch),
                    Meal("Večera", "7:00 PM - 8€ uštede", R.drawable.dinnermain)
                )
            ),
            MealPlan(
                dayTitle = "Četvrtak",
                meals = listOf(
                    Meal("Doručak", "8:00 AM - 5€ uštede", R.drawable.breakfast),
                    Meal("Ručak", "12:30 PM - 10€ uštede", R.drawable.lunch),
                    Meal("Večera", "7:00 PM - 8€ uštede", R.drawable.dinnermain)
                )
            ),
            MealPlan(
                dayTitle = "Petak",
                meals = listOf(
                    Meal("Doručak", "8:00 AM - 11€ uštede", R.drawable.breakfast),
                    Meal("Ručak", "12:30 PM - 10€ uštede", R.drawable.lunch),
                    Meal("Večera", "7:00 PM - 9€ uštede", R.drawable.dinnermain)
                )
            ),
            MealPlan(
                dayTitle = "Subota",
                meals = listOf(
                    Meal("Doručak", "8:00 AM - 5€ uštede", R.drawable.breakfast),
                    Meal("Ručak", "12:30 PM - 10€ uštede", R.drawable.lunch),
                    Meal("Večera", "7:00 PM - 8€ uštede", R.drawable.dinnermain)
                )
            ),
            MealPlan(
                dayTitle = "Nedjelja",
                meals = listOf(
                    Meal("Doručak", "8:00 AM - 8€ uštede", R.drawable.breakfast),
                    Meal("Ručak", "12:30 PM - 2€ uštede", R.drawable.lunch),
                    Meal("Večera", "7:00 PM - 3€ uštede", R.drawable.dinnermain)
                )
            ),
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WeekPlan().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
