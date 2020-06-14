package com.github.anastasiazhukova.fitness.domain.weight

import com.github.anastasiazhukova.fitness.datasource.user.weight.WeightDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper

class WeightDataModelMapper : IMapper<WeightDataModel, WeightModel?> {
    override fun invoke(data: WeightDataModel): WeightModel? {
        return if (data.id.isNotEmpty() && data.date != null && data.weight != null) {
            WeightModel(
                id = data.id,
                date = data.date,
                weight = data.weight
            )
        } else {
            null
        }
    }
}