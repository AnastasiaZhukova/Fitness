package com.github.anastasiazhukova.fitness.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.utils.extensions.gone
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.view_workout_break.view.*
import kotlinx.android.synthetic.main.view_workout_plan_ready.view.*
import kotlinx.android.synthetic.main.view_workout_ready.view.*
import kotlinx.android.synthetic.main.view_workout_ready.view.workoutCalories
import kotlinx.android.synthetic.main.view_workout_ready.view.workoutDuration

class WorkoutPlanCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.AppRoundedCardView
) : MaterialCardView(context, attrs, defStyleAttr) {

    private var isWorkoutReadyMode = false

    init {
        View.inflate(context, R.layout.view_workout_plan_break, this)
    }

    fun setWorkoutBreakMode() {
        isWorkoutReadyMode = false
        changeMode(R.layout.view_workout_plan_break)
    }

    fun setWorkoutReadyMode() {
        isWorkoutReadyMode = true
        changeMode(R.layout.view_workout_plan_ready)
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

    fun setWorkoutCalories(calories: Int?) {
        if (!isWorkoutReadyMode) {
            setWorkoutReadyMode()
        }

        if (calories == null || calories <= 0) {
            workoutCalories.gone()
        } else {
            workoutCalories.text =
                context.getString(R.string.workout_calories_label).format(calories)
        }
    }

    fun setWorkoutDuration(duration: Int?) {
        if (!isWorkoutReadyMode) {
            setWorkoutReadyMode()
        }

        if (duration == null || duration <= 0) {
            workoutDuration.gone()
        } else {
            workoutDuration.text =
                context.getString(R.string.workout_duration_label).format(duration)
        }
    }

    fun setWorkoutLevel(level: String?) {
        if (!isWorkoutReadyMode) {
            setWorkoutReadyMode()
        }

        if (level.isNullOrEmpty()) {
            workoutLevel.gone()
        } else {
            workoutLevel.text =
                context.getString(R.string.workout_level_label).format(level)
        }
    }

    fun setStartWorkoutButtonClickListener(clickListener: OnClickListener) {
        if (!isWorkoutReadyMode) {
            setWorkoutReadyMode()
        }

        startButton.setOnClickListener(clickListener)
    }

    private fun changeMode(layout: Int) {
        removeAllViews()
        View.inflate(context, layout, this)
        invalidate()
    }
}