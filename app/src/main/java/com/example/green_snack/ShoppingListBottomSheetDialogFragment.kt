// ShoppingListBottomSheetDialogFragment.kt
package com.example.green_snack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.green_snack.databinding.ShoppingListBottomSheetBinding

class ShoppingListBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: ShoppingListBottomSheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ShoppingListAdapter
    private var shoppingList: ArrayList<ShoppingItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            shoppingList = it.getParcelableArrayList(ARG_SHOPPING_LIST) ?: arrayListOf()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = ShoppingListBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ShoppingListAdapter(shoppingList)
        binding.shoppingListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.shoppingListRecyclerView.adapter = adapter

        binding.closeButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_SHOPPING_LIST = "shopping_list"

        fun newInstance(shoppingList: ArrayList<ShoppingItem>): ShoppingListBottomSheetDialogFragment {
            val fragment = ShoppingListBottomSheetDialogFragment()
            fragment.arguments = bundleOf(ARG_SHOPPING_LIST to shoppingList)
            return fragment
        }
    }
}
