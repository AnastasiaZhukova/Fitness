package com.github.anastasiazhukova.statistics.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.domain.water.WaterEntry
import com.github.anastasiazhukova.statistics.R
import com.google.android.material.textview.MaterialTextView

class WaterAdapter : RecyclerView.Adapter<WaterAdapter.CaloriesViewHolder>() {

    var items: List<WaterEntry> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaloriesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_adapter_water, parent, false)

        return CaloriesViewHolder(view)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: CaloriesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class CaloriesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val waterLabel = view.context.getString(R.string.ml)

        @SuppressLint("SetTextI18n")
        fun bind(model: WaterEntry) {
            val type = view.findViewById<MaterialTextView>(R.id.type)
            val amount = view.findViewById<MaterialTextView>(R.id.amount)

            type.text = model.type
            amount.text = "${model.amount} $waterLabel"
        }
    }
}

