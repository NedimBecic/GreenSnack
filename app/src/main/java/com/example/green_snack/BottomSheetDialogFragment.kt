package com.example.green_snack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.green_snack.databinding.BottomSheetMealBinding

class MealBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetMealBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_MEAL_TITLE = "meal_title"
        private const val ARG_MEAL_INGREDIENTS = "meal_ingredients"
        private const val ARG_MEAL_BENEFITS = "meal_benefits"

        fun newInstance(
            title: String,
            ingredients: List<String>,
            benefits: List<String>
        ): MealBottomSheetFragment {
            val fragment = MealBottomSheetFragment()
            val args = Bundle().apply {
                putString(ARG_MEAL_TITLE, title)
                putStringArrayList(ARG_MEAL_INGREDIENTS, ArrayList(ingredients))
                putStringArrayList(ARG_MEAL_BENEFITS, ArrayList(benefits))
            }
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BottomSheetMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mealTitle = arguments?.getString(ARG_MEAL_TITLE) ?: "Jelo"
        val mealIngredients = arguments?.getStringArrayList(ARG_MEAL_INGREDIENTS) ?: arrayListOf()
        val mealBenefits = arguments?.getStringArrayList(ARG_MEAL_BENEFITS) ?: arrayListOf()

        binding.mealTitle.text = mealTitle

        binding.mealIngredients.text = mealIngredients.joinToString("\n") { "• $it" }
        binding.mealBenefits.text = mealBenefits.joinToString("\n") { "• $it" }

        binding.closeButton.setOnClickListener {
            dismiss()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
