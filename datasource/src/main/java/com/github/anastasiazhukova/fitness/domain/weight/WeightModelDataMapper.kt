package com.github.anastasiazhukova.fitness.domain.weight

import com.github.anastasiazhukova.fitness.datasource.user.weight.WeightDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper

class WeightModelDataMapper : IMapper<WeightModel, WeightDataModel> {
    override fun invoke(weightModel: WeightModel): WeightDataModel {
        return WeightDataModel(
            id = weightModel.id,
            date = weightModel.date,
            weight = weightModel.weight
        )
    }
}