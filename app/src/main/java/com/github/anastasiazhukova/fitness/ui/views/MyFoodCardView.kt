package com.github.anastasiazhukova.fitness.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.github.anastasiazhukova.fitness.R
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.view_my_food.view.*

class MyFoodCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.AppCardView
) : MaterialCardView(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_my_food, this)
    }

    fun setCalories(calories: String?) {
        if (calories.isNullOrEmpty()) {
            myFoodText.setText(R.string.no_data_label)
        } else {
            myFoodText.text = calories
        }
    }
}