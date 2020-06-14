package com.github.anastasiazhukova.fitness.utils.extensions

import android.view.View
import android.widget.EditText
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY

fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.invisible() {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
}

fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

fun View.disable() {
    if (isEnabled) {
        isEnabled = false
    }
}

fun View.enable() {
    if (!isEnabled) {
        isEnabled = true
    }
}

fun EditText?.isNullOrEmpty() = this?.text.isNullOrEmpty()

fun EditText?.charactersCount() = this?.text?.length ?: 0

fun EditText?.getTextAsString() = this?.text?.toString() ?: EMPTY

fun EditText?.asInt(): Int {
    this?.text?.toString()?.takeIf { it.isNotEmpty() }?.let {
        return it.toInt()
    }

    return -1
}

fun EditText?.asFloat(): Float {
    this?.text?.toString()?.takeIf { it.isNotEmpty() }?.let {
        return it.toFloat()
    }

    return -1.0f
}