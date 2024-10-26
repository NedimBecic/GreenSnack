package com.example.green_snack

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Stats : Fragment() {

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

        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        val barChart = view.findViewById<BarChart>(R.id.chart)
        setupBarChart(barChart)

        val lineChart = view.findViewById<LineChart>(R.id.lineChart)
        setupLineChart(lineChart)

        val pieChart = view.findViewById<PieChart>(R.id.pieChart)
        setupPieChart(pieChart)

        return view
    }

    private fun setupBarChart(chart: BarChart) {
        chart.setDrawGridBackground(false)
        chart.axisLeft.setDrawGridLines(false)
        chart.axisRight.setDrawGridLines(false)
        chart.xAxis.setDrawGridLines(false)
        chart.setDrawBorders(false)

        val entries = ArrayList<BarEntry>()

        entries.add(BarEntry(1f, 5f))  // Week 1
        entries.add(BarEntry(2f, 7f))  // Week 2
        entries.add(BarEntry(3f, 8f))  // Week 3
        entries.add(BarEntry(4f, 6f))  // Week 4

        val dataSet = BarDataSet(entries, "Spašeni otpad hrane (kg)")
        dataSet.color = Color.parseColor("#009b7e")
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 12f

        val barData = BarData(dataSet)
        chart.data = barData
        chart.description.isEnabled = false
        chart.axisLeft.axisMinimum = 0f
        chart.xAxis.axisMinimum = 0f
        chart.axisRight.isEnabled = false
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.invalidate()
        chart.animateX(1000)
    }

    private fun setupLineChart(chart: LineChart) {
        val entries = ArrayList<Entry>()

        entries.add(Entry(1f, 20f))  // Week 1
        entries.add(Entry(2f, 35f))  // Week 2
        entries.add(Entry(3f, 30f))  // Week 3
        entries.add(Entry(4f, 40f))  // Week 4

        val dataSet = LineDataSet(entries, "Spriječene CO2 emisije (kg)")
        dataSet.color = Color.parseColor("#FF7043")
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 12f

        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.description.isEnabled = false
        chart.axisLeft.axisMinimum = 0f
        chart.xAxis.axisMinimum = 0f
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.axisRight.isEnabled = false
        chart.invalidate()
        chart.animateX(1000)
    }

    private fun setupPieChart(chart: PieChart) {
        val entries = ArrayList<PieEntry>()
        // Sample data for Money Saved (in currency, e.g., USD)
        entries.add(PieEntry(30f, "Sedmica 1"))
        entries.add(PieEntry(50f, "Sedmica 2"))
        entries.add(PieEntry(40f, "Sedmica 3"))
        entries.add(PieEntry(60f, "Sedmica 4"))

        val dataSet = PieDataSet(entries, "Ušteđeni novac")
        dataSet.colors = listOf(
            Color.parseColor("#FFA726"),
            Color.parseColor("#66BB6A"),
            Color.parseColor("#42A5F5"),
            Color.parseColor("#AB47BC")
        )
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 12f

        val pieData = PieData(dataSet)
        chart.data = pieData
        chart.description.isEnabled = false
        chart.invalidate()
        chart.animateXY(1000, 1000)
    }

    companion object {
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
