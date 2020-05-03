package com.github.anastasiazhukova.fitness.screens.fragments.water.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.screens.fragments.water.domain.WaterEntry
import com.google.android.material.textview.MaterialTextView

class WaterAdapter : RecyclerView.Adapter<WaterAdapter.WaterViewHolder>() {

    var items: List<WaterEntry> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_adapter_water, parent, false)

        return WaterViewHolder(view)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: WaterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class WaterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(model: WaterEntry) {
            val text = view.findViewById<MaterialTextView>(R.id.text)
            text.text = "${model.type} ${model.amount}"
        }
    }
}

