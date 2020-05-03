package com.github.anastasiazhukova.fitness.screens.fragments.statistics.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.domain.StatisticsModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.textview.MaterialTextView

class StatisticsAdapter : RecyclerView.Adapter<StatisticsAdapter.StatisticsViewHolder>() {

    var items: List<StatisticsModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_adapter_statistics_bar_chart, parent, false)

        return StatisticsViewHolder(view)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: StatisticsViewHolder, position: Int) {
        holder.bind(items[position])
    }


    inner class StatisticsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(model: StatisticsModel) {
            val sectionTitle = view.findViewById<MaterialTextView>(R.id.sectionTitle)
            val chart = view.findViewById<BarChart>(R.id.statisticsChart)

            sectionTitle.text = model.label

            val barEntries = model.values.map { BarEntry(it.day.toFloat(), it.value) }
            val barDataSet = BarDataSet(barEntries, model.label)


            chart.configure(barDataSet)
        }

        private fun BarChart.configure(barDataSet: BarDataSet) {
            barDataSet.color = view.context.getColor(R.color.primary)
            animateY(1000)
            xAxis.granularity = 1.0f
            setMaxVisibleValueCount(10)
            data = BarData(barDataSet)
        }
    }
}