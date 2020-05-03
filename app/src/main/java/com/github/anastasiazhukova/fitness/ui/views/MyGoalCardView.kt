package com.github.anastasiazhukova.fitness.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.github.anastasiazhukova.fitness.R
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.view_my_goal.view.*

class MyGoalCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.AppCardView
) : MaterialCardView(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_my_goal, this)
    }

    fun setWeightGoal(weightGoal: String?) {
        if (weightGoal.isNullOrEmpty()) {
            weightText.setText(R.string.no_data_label)
        } else {
            weightText.text = weightGoal
        }
    }

    fun setCaloriesGoal(caloriesGoal: String?) {
        if (caloriesGoal.isNullOrEmpty()) {
            caloriesText.setText(R.string.no_data_label)
        } else {
            caloriesText.text = caloriesGoal
        }
    }

    fun setWaterGoal(waterGoal: String?) {
        if (waterGoal.isNullOrEmpty()) {
            waterText.setText(R.string.no_data_label)
        } else {
            waterText.text = waterGoal
        }
    }
}