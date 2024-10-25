package com.example.green_snack

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Stats.newInstance] factory method to
 * create an instance of this fragment.
 */
class Stats : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stats, container, false)
        val chart = view.findViewById<BarChart>(R.id.chart);

        chart.setDrawGridBackground(false);
        chart.axisLeft.setDrawGridLines(false);
        chart.axisRight.setDrawGridLines(false);
        chart.xAxis.setDrawGridLines(false);
        chart.setDrawBorders(false);


        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(1f, 2f))
        entries.add(BarEntry(2f, 4f))
        entries.add(BarEntry(30f, 6f))

        // Kreiraj DataSet i dodaj ga u podatke grafikona
        val dataSet = BarDataSet(entries, "Label")
        dataSet.color = resources.getColor(R.color.primary)
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 9f

        // Kreiraj LineData sa DataSet-om
        val lineData = BarData(dataSet)
        chart.data = lineData

        // Postavi izgled grafikona
        chart.description.isEnabled = false // Isključuje opis
        chart.axisLeft.axisMinimum = 0f // Minimalna vrednost na Y-osi
        chart.xAxis.axisMinimum = 0f // Minimalna vrednost na X-osi
        //chart.xAxis.granularity = 1f // Postavi granularity X-osi

        chart.axisRight.isEnabled = false
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM

        // Osveži grafikon da prikaže nove podatke
        chart.invalidate()
        chart.animateX(1000)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Stats.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Stats().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}