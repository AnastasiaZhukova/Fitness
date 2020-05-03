package com.github.anastasiazhukova.fitness.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.R
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.view_elements_list.view.*
import kotlinx.android.synthetic.main.view_my_food.view.addButton

interface IElementsListListener {
    fun onAddElement()
    fun onDeleteElement(position: Int)
}

class ElementsListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.AppRoundedCardView
) : MaterialCardView(context, attrs, defStyleAttr) {

    private var listener: IElementsListListener? = null

    init {
        View.inflate(context, R.layout.view_elements_list, this)

        addButton?.setOnClickListener {
            listener?.onAddElement()
        }
    }

    fun getItemsList(): RecyclerView = list

    fun setListener(listener: IElementsListListener) {
        this.listener = listener
    }
}