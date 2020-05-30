package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain

import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY

class ClientsDataModelMapper : IMapper<UserInfoDataModel, ClientModel> {

    override fun invoke(userInfoDataModel: UserInfoDataModel): ClientModel {
        return ClientModel(
            id = userInfoDataModel.id,
            name = userInfoDataModel.name ?: EMPTY,
            gender = userInfoDataModel.gender,
            height = userInfoDataModel.userParams?.height,
            weight = userInfoDataModel.userParams?.weight,
            fitnessLevel = userInfoDataModel.userParams?.fitnessLevel,
            goalCalories = userInfoDataModel.userGoal?.goalCalories,
            goalWater = userInfoDataModel.userGoal?.goalWater
        )
    }
}