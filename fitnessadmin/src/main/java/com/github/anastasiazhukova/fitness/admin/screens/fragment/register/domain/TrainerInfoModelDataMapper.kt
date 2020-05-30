package com.github.anastasiazhukova.fitness.admin.screens.fragment.register.domain

import com.github.anastasiazhukova.fitness.datasource.trainer.TrainerInfoDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper

class TrainerInfoModelDataMapper : IMapper<TrainerInfo, TrainerInfoDataModel> {
    override fun invoke(userInfo: TrainerInfo): TrainerInfoDataModel {
        return TrainerInfoDataModel(
            id = userInfo.id,
            name = userInfo.name,
            nickname = userInfo.nickname
        )
    }
}