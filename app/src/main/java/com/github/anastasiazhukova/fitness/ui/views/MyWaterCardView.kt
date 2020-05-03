package com.github.anastasiazhukova.fitness.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.github.anastasiazhukova.fitness.R
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.view_my_water.view.*

class MyWaterCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.AppCardView
) : MaterialCardView(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_my_water, this)
    }

    fun setWater(water: String?) {
        if (water.isNullOrEmpty()) {
            myWaterText.setText(R.string.no_data_label)
        } else {
            myWaterText.text = water
        }
    }
}