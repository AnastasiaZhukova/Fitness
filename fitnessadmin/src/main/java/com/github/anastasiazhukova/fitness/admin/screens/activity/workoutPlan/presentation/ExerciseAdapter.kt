package com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.admin.R
import com.github.anastasiazhukova.fitness.domain.workoutPlan.ExerciseEntry
import com.github.anastasiazhukova.fitness.utils.extensions.toSecondsAsInt
import com.google.android.material.textview.MaterialTextView

interface IExerciseClickListener {
    fun onEditClicked(model: ExerciseEntry)
}

class ExerciseAdapter : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    private var exerciseClickListener: IExerciseClickListener? = null

    var items: List<ExerciseEntry> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_adapter_exercise_entry, parent, false)

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

        private val secondsAsText = view.resources.getString(R.string.sec)

        fun bind(model: ExerciseEntry) {
            val exerciseName = view.findViewById<MaterialTextView>(R.id.exerciseName)
            val exerciseTime = view.findViewById<MaterialTextView>(R.id.exerciseTime)
            val editButton = view.findViewById<MaterialTextView>(R.id.editButton)
            val exerciseSeconds = "${model.timeInMillis.toSecondsAsInt()} $secondsAsText"

            exerciseName.text = model.name
            exerciseTime.text = exerciseSeconds
            editButton.setOnClickListener {
                exerciseClickListener?.onEditClicked(model)
            }
        }
    }
}

