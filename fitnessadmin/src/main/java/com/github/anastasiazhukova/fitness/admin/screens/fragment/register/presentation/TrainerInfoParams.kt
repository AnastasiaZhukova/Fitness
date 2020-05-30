package com.github.anastasiazhukova.fitness.admin.screens.fragment.register.presentation

import com.github.anastasiazhukova.fitness.admin.screens.fragment.register.domain.TrainerInfo

data class TrainerInfoParams(
    val name: String,
    val nickname: String
)

fun TrainerInfoParams.toTrainerInfo(userId: String) =
    TrainerInfo(
        id = userId,
        name = this.name,
        nickname = this.nickname
    )