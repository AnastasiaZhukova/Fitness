package com.github.anastasiazhukova.fitness.screens.fragments.water.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.domain.water.WaterEntry
import com.google.android.material.textview.MaterialTextView

interface IWaterClickListener {
    fun onEditClicked(model: WaterEntry)
}

class WaterAdapter : RecyclerView.Adapter<WaterAdapter.WaterViewHolder>() {

    private var waterClickListener: IWaterClickListener? = null

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

    fun setWaterClickListener(listener: IWaterClickListener) {
        waterClickListener = listener
    }

    inner class WaterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val mlLabel = view.context.resources.getString(R.string.ml)

        fun bind(model: WaterEntry) {
            val type = view.findViewById<MaterialTextView>(R.id.type)
            val amount = view.findViewById<MaterialTextView>(R.id.amount)
            type.text = "${model.type}"
            amount.text = "${model.amount} $mlLabel"

            view.setOnClickListener {
                waterClickListener?.onEditClicked(model)
            }
        }
    }
}

