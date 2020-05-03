package com.github.anastasiazhukova.fitness.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.fragment.app.FragmentManager
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.utils.extensions.toReadableDate
import com.google.android.material.card.MaterialCardView
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.view_date_picker.view.*

interface IDatePickerSupport {
    fun getFragmentManagerForDatePicker(): FragmentManager?
}

interface IDatePickerListener {
    fun onDatePicked(date: Long)
}

class DatePickerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.AppRoundedCardView
) : MaterialCardView(context, attrs, defStyleAttr) {

    val TAG = "DATE_PICKER"

    private val picker: MaterialDatePicker<Long>

    private var currentlySelectedDate: Long? = null
    private var datePickerSupport: IDatePickerSupport? = null
    private var datePickerListener: IDatePickerListener? = null

    init {
        View.inflate(context, R.layout.view_date_picker, this)

        picker = MaterialDatePicker.Builder.datePicker().build()
        picker.addOnPositiveButtonClickListener {
            setCurrentlySelectedDate(it)
            datePickerListener?.onDatePicked(it)
        }

        setOnClickListener { showDatePicker() }
    }

    fun setDatePickerSupport(datePickerSupport: IDatePickerSupport?) {
        this.datePickerSupport = datePickerSupport
    }

    fun setDatePickerListener(datePickerListener: IDatePickerListener?) {
        this.datePickerListener = datePickerListener
    }

    fun setCurrentlySelectedDate(date: Long) {
        currentlySelectedDate = date
        text.text = date.toReadableDate()
    }

    private fun showDatePicker() {
        datePickerSupport?.getFragmentManagerForDatePicker()?.let { fragmentManager ->
            picker.show(fragmentManager, TAG)
        }
    }
}