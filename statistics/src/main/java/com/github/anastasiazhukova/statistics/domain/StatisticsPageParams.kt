package com.github.anastasiazhukova.statistics.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatisticsPageParams(
    val clientId: String
) : Parcelable