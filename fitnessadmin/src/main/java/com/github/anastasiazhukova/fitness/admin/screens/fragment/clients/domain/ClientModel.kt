package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain

import com.github.anastasiazhukova.fitness.admin.screens.common.clientDetails.ClientDetailsParams

data class ClientModel(
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
)

fun ClientModel.toClientDetailsParams(): ClientDetailsParams =
    ClientDetailsParams(
        id = id,
        name = name,
        trainerNickname = trainerNickname,
        gender = gender,
        height = height,
        weight = weight,
        fitnessLevel = fitnessLevel,
        goalCalories = goalCalories,
        goalWater = goalWater,
        goalWeight = goalWeight
    )