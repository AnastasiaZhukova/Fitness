package com.github.anastasiazhukova.fitness.admin.screens.common.clientDetails

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClientDetailsParams(
    val id: String,
    val name: String,
    val trainerNickname: String,
    val gender: Boolean,
    val height: Int?,
    val weight: Float?,
    val fitnessLevel: Int?,
    val goalCalories: Int?,
    val goalWater: Int?,
    val goalWeight: Int?
) : Parcelable