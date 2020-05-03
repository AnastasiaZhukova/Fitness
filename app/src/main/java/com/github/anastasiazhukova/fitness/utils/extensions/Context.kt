package com.github.anastasiazhukova.fitness.utils.extensions

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.github.anastasiazhukova.fitness.utils.constants.Constants.Extra.ACTIVITY_EXTRA

fun Context.startActivity(activityClass: Class<*>, extras: Parcelable? = null) {
    val intent = Intent(this, activityClass)
    extras?.let {
        intent.putExtra(ACTIVITY_EXTRA, extras)
    }

    startActivity(intent)
}

fun <T : Parcelable> Intent.getActivityExtra(): T? {
    return getParcelableExtra<T>(ACTIVITY_EXTRA)
}