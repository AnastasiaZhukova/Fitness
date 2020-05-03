package com.github.anastasiazhukova.fitness.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.utils.extensions.gone
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.view_rating.view.*

class RatingCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.AppRoundedCardView
) : MaterialCardView(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_rating, this)
    }

    fun setRatingValue(value: String?) {
        if (value.isNullOrEmpty()) {
            ratingText.setText(R.string.no_data_label)
        } else {
            ratingText.text = value
        }
    }

    fun setLabel(label: String?) {
        if (label.isNullOrEmpty()) {
            ratingLabel.gone()
        } else {
            ratingLabel.text = label
        }
    }
}