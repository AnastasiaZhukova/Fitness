package com.github.anastasiazhukova.statistics.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY
import com.github.anastasiazhukova.fitness.utils.extensions.gone
import com.github.anastasiazhukova.fitness.utils.extensions.invisible
import com.github.anastasiazhukova.fitness.utils.extensions.toShortReadableDate
import com.github.anastasiazhukova.fitness.utils.extensions.visible
import com.github.anastasiazhukova.statistics.R
import com.github.anastasiazhukova.statistics.domain.StatisticsEntry
import com.github.anastasiazhukova.statistics.domain.StatisticsModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.google.android.material.textview.MaterialTextView

interface IValueSelectedListener {
    fun onValueSelected(entry: StatisticsEntry)
}

class StatisticsAdapter : RecyclerView.Adapter<StatisticsAdapter.StatisticsViewHolder>() {

    private var valueSelectedListener: IValueSelectedListener? = null
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

    fun setValueSelectedListener(valueSelectedListener: IValueSelectedListener) {
        this.valueSelectedListener = valueSelectedListener
    }

    inner class StatisticsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(model: StatisticsModel) {
            val sectionTitle = view.findViewById<MaterialTextView>(R.id.sectionTitle)
            val noDataLabel = view.findViewById<MaterialTextView>(R.id.noDataLabel)
            val chart = view.findViewById<BarChart>(R.id.statisticsChart)

            sectionTitle.text = model.label

            if (model.values.isEmpty()) {
                noDataLabel.visible()
                chart.invisible()
            } else {
                noDataLabel.gone()
                chart.configure(model)
            }
        }

        private fun BarChart.configure(model: StatisticsModel) {
            visible()

            val barDataSet = BarDataSet(getMappedBarEntries(model), model.label)
            legend.isEnabled = false
            description.isEnabled = false
            xAxis?.apply {
                valueFormatter = DayAxisValueFormatter(model)
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                granularity = 1f
            }
            barDataSet.color = view.context.getColor(R.color.primary)
            data = BarData(barDataSet)
            animateY(1000)
            invalidate()

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onNothingSelected() {}

                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    val entryIndex = barDataSet.getEntryIndex(e)
                    val values = model.values

                    if (entryIndex < values.size) {
                        valueSelectedListener?.onValueSelected(values[entryIndex])
                    }
                }
            })
        }

        private fun getMappedBarEntries(model: StatisticsModel): List<BarEntry> {
            return model.values.mapIndexed { index, statisticsEntry ->
                BarEntry(
                    index.toFloat(),
                    statisticsEntry.value
                )
            }
        }

        private inner class DayAxisValueFormatter(
            private val model: StatisticsModel
        ) : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val index = value.toInt()
                val items = model.values

                return if (index < items.size) {
                    items[index].date.toShortReadableDate()
                } else {
                    EMPTY
                }
            }
        }
    }
}