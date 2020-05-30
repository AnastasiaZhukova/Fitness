package com.github.anastasiazhukova.fitness.screens.fragments.register.domain

import com.github.anastasiazhukova.fitness.datasource.user.info.UserGoal
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDataModel
import com.github.anastasiazhukova.fitness.datasource.user.info.UserParams
import com.github.anastasiazhukova.fitness.utils.IMapper

class UserInfoModelDataMapper : IMapper<UserInfo, UserInfoDataModel> {
    override fun invoke(userInfo: UserInfo): UserInfoDataModel {
        return UserInfoDataModel(
            id = userInfo.id,
            name = userInfo.name,
            gender = userInfo.gender,
            userParams = UserParams(
                height = userInfo.height,
                weight = userInfo.weight,
                fitnessLevel = userInfo.fitnessLevel
            ),
            userGoal = UserGoal(
                goalCalories = userInfo.goalCalories,
                goalWater = userInfo.goalWater
            )
        )
    }
}