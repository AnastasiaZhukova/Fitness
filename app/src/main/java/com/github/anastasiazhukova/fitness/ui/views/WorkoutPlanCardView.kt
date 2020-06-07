package com.github.anastasiazhukova.fitness.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.utils.extensions.gone
import com.github.anastasiazhukova.fitness.utils.extensions.toReadableTime
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.view_workout_plan_break.view.*
import kotlinx.android.synthetic.main.view_workout_plan_ready.view.*

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
        if (isWorkoutReadyMode) {
            isWorkoutReadyMode = false
            changeMode(R.layout.view_workout_plan_break)
        }
    }

    fun setWorkoutReadyMode() {
        if (!isWorkoutReadyMode) {
            isWorkoutReadyMode = true
            changeMode(R.layout.view_workout_plan_ready)
        }
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
        if (calories == null || calories <= 0) {
            workoutCalories.gone()
        } else {
            workoutCalories.text =
                context.getString(R.string.workout_calories_label).format(calories)
        }
    }

    fun setWorkoutDuration(duration: Int?) {
        if (duration == null || duration <= 0) {
            workoutDuration.gone()
        } else {
            val readableTime = duration.toReadableTime(
                resources.getString(R.string.sec),
                resources.getString(R.string.min)
            )

            workoutDuration.text =
                context.getString(R.string.workout_duration_label).format(readableTime)
        }
    }

    fun setWorkoutLevel(level: String?) {
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