package com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.admin.R
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.domain.ExerciseModel
import com.google.android.material.textview.MaterialTextView

interface IExerciseClickListener {
    fun onEditClicked(model: ExerciseModel)
}

class ExerciseAdapter : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    private var exerciseClickListener: IExerciseClickListener? = null

    var items: List<ExerciseModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_adapter_exercise, parent, false)

        return ExerciseViewHolder(view)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setExerciseEditClickListener(listener: IExerciseClickListener) {
        exerciseClickListener = listener
    }

    inner class ExerciseViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(model: ExerciseModel) {
            val text = view.findViewById<MaterialTextView>(R.id.exerciseName)
            val editButton = view.findViewById<MaterialTextView>(R.id.editButton)

            text.text = model.name
            editButton.setOnClickListener {
                exerciseClickListener?.onEditClicked(model)
            }
        }
    }
}

