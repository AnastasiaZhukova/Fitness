package com.github.anastasiazhukova.fitness.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.github.anastasiazhukova.fitness.R
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.view_workout_break.view.*
import kotlinx.android.synthetic.main.view_workout_ready.view.*

class WorkoutCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.AppCardView
) : MaterialCardView(context, attrs, defStyleAttr) {

    private var isWorkoutReadyMode = false

    init {
        View.inflate(context, R.layout.view_workout_break, this)
    }

    fun setWorkoutBreakMode() {
        isWorkoutReadyMode = false
        changeMode(R.layout.view_workout_break)
    }

    fun setWorkoutReadyMode() {
        isWorkoutReadyMode = true
        changeMode(R.layout.view_workout_ready)
    }

    fun setNextWorkoutDay(workoutDay: String?) {
        if (isWorkoutReadyMode) {
            setWorkoutBreakMode()
        }

        if (workoutDay.isNullOrEmpty()) {
            breakText.setText(R.string.workout_break_text)
        } else {
            breakText.text =
                context.getString(R.string.workout_break_text_with_param).format(workoutDay)
        }
    }

    fun setWorkoutCalories(calories: String?) {
        if (!isWorkoutReadyMode) {
            setWorkoutReadyMode()
        }

        if (calories.isNullOrEmpty()) {
            workoutCalories.setText(R.string.no_data_label)
        } else {
            workoutCalories.text = calories
        }
    }

    fun setWorkoutDuration(duration: String?) {
        if (!isWorkoutReadyMode) {
            setWorkoutReadyMode()
        }

        if (duration.isNullOrEmpty()) {
            workoutDuration.setText(R.string.no_data_label)
        } else {
            workoutDuration.text = duration
        }
    }

    fun setStartWorkoutButtonClickListener(clickListener: OnClickListener) {
        if (!isWorkoutReadyMode) {
            setWorkoutReadyMode()
        }

        startWorkoutButton.setOnClickListener(clickListener)
    }

    private fun changeMode(layout: Int) {
        removeAllViews()
        View.inflate(context, layout, this)
        invalidate()
    }
}