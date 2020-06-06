package com.github.anastasiazhukova.fitness.admin.screens.activity.clientDetails.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClientDetailsParams(
    val id: String,
    val name: String,
    val gender: Boolean,
    val height: Int?,
    val weight: Int?,
    val fitnessLevel: Int?,
    val goalCalories: Int?,
    val goalWater: Int?,
    val goalWeight: Int?
) : Parcelable