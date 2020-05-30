package com.github.anastasiazhukova.fitness.screens.fragments.register.presentation

import com.github.anastasiazhukova.fitness.screens.fragments.register.domain.UserInfo

data class UserInfoParams(
    val name: String,
    val gender: Boolean,
    val height: Int,
    val weight: Int,
    val fitnessLevel: Int,
    val goalCalories: Int,
    val goalWater: Int
)

fun UserInfoParams.toUserInfo(userId: String) =
    UserInfo(
        id = userId,
        name = this.name,
        gender = this.gender,
        height = this.height,
        weight = this.weight,
        fitnessLevel = this.fitnessLevel,
        goalCalories = this.goalCalories,
        goalWater = this.goalWater
    )