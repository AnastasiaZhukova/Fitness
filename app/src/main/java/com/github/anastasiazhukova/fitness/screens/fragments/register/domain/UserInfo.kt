package com.github.anastasiazhukova.fitness.screens.fragments.register.domain

data class UserInfo(
    val id: String,
    val name: String,
    val trainerNickname: String,
    val gender: Boolean,
    val height: Int,
    val weight: Float,
    val fitnessLevel: Int,
    val goalCalories: Int,
    val goalWater: Int
)