package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain

data class ClientModel(
    val id: String,
    val name: String,
    val gender: Boolean,
    val height: Int?,
    val weight: Int?,
    val fitnessLevel: Int?,
    val goalCalories: Int?,
    val goalWater: Int?
)