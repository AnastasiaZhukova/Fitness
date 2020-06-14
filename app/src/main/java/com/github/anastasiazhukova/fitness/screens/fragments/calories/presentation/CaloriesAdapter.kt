package com.github.anastasiazhukova.fitness.screens.fragments.calories.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.domain.calories.CaloriesEntry
import com.github.anastasiazhukova.fitness.utils.extensions.calculateCalories
import com.google.android.material.textview.MaterialTextView

interface ICaloriesClickListener {
    fun onEditClicked(model: CaloriesEntry)
}

class CaloriesAdapter : RecyclerView.Adapter<CaloriesAdapter.CaloriesViewHolder>() {

    private var caloriesClickListener: ICaloriesClickListener? = null

    var items: List<CaloriesEntry> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaloriesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_adapter_calories, parent, false)

        return CaloriesViewHolder(view)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: CaloriesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setCaloriesClickListener(listener: ICaloriesClickListener) {
        caloriesClickListener = listener
    }

    inner class CaloriesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val caloriesLabel = view.context.resources.getString(R.string.cal)

        fun bind(model: CaloriesEntry) {
            val type = view.findViewById<MaterialTextView>(R.id.type)
            val amount = view.findViewById<MaterialTextView>(R.id.amount)
            type.text = model.name
            val cal = calculateCalories(model.weight, model.calories)
            amount.text = "$cal $caloriesLabel"

            view.setOnClickListener {
                caloriesClickListener?.onEditClicked(model)
            }
        }
    }
}

