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
        private const val ARG_MEAL_RECIPE = "meal_recipe"

        fun newInstance(title: String, recipe: String): MealBottomSheetFragment {
            val fragment = MealBottomSheetFragment()
            val args = Bundle()
            args.putString(ARG_MEAL_TITLE, title)
            args.putString(ARG_MEAL_RECIPE, recipe)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = BottomSheetMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mealTitle = arguments?.getString(ARG_MEAL_TITLE)
        val mealRecipe = arguments?.getString(ARG_MEAL_RECIPE)

        binding.mealTitle.text = mealTitle
        binding.mealRecipe.text = mealRecipe
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
