package com.example.green_snack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Profile : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    // Called to create the view hierarchy associated with the Fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)


        val spinner = view.findViewById<Spinner>(R.id.genderSpinner)
        val genderOptions = listOf("Odaberi spol", "Muški", "Ženski", "Ostali")

        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, genderOptions)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)

        spinner.adapter = adapter
        spinner.setSelection(0)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4) // Changed to 2 columns for better visibility
        recyclerView.addItemDecoration(SpaceItemDecoration(16))


        val categories = CategoryData.getCategories()
        categoryAdapter = CategoryAdapter(categories)

        recyclerView.adapter = categoryAdapter

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
