package com.github.anastasiazhukova.fitness.domain.water

import com.github.anastasiazhukova.fitness.datasource.user.water.WaterDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper

class WaterDataModelMapper :
    IMapper<WaterDataModel, WaterModel?> {
    override fun invoke(data: WaterDataModel): WaterModel? {
        val waterEntries = data.waterEntries.mapNotNull { entry ->
            if (entry.id.isNotEmpty() && !entry.type.isNullOrEmpty() && entry.amount != null) {
                WaterEntry(
                    id = entry.id,
                    type = entry.type!!,
                    amount = entry.amount!!
                )
            } else {
                null
            }
        }

        return if (data.id.isNotEmpty() && data.date != null) {
            WaterModel(
                id = data.id,
                date = data.date!!,
                entries = waterEntries
            )
        } else {
            null
        }
    }
}