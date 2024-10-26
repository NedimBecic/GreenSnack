package com.example.green_snack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.green_snack.databinding.ItemMealPlanBinding

data class MealPlan(
    val dayTitle: String,
    val meals: List<Meal>
)

data class Meal(
    val mealType: String,
    val timeAndSavings: String,
    val imageResId: Int
)

class WeekPlanAdapter(private val weekPlans: List<MealPlan>) :
    RecyclerView.Adapter<WeekPlanAdapter.WeekPlanViewHolder>() {
        class WeekPlanViewHolder(val binding: ItemMealPlanBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekPlanViewHolder {

        val binding = ItemMealPlanBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeekPlanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeekPlanViewHolder, position: Int) {
        val mealPlan = weekPlans[position]
        holder.binding.mealPlanDayTitle.text = mealPlan.dayTitle

    }

    override fun getItemCount(): Int = weekPlans.size
}
