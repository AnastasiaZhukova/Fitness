package com.github.anastasiazhukova.fitness.screens.fragments.water.domain

import com.github.anastasiazhukova.fitness.datasource.user.water.WaterDataModel
import com.github.anastasiazhukova.fitness.datasource.user.water.WaterEntryDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper

class WaterModelDataMapper : IMapper<WaterModel, WaterDataModel> {
    override fun invoke(waterModel: WaterModel): WaterDataModel {
        val waterEntriesData = waterModel.entries.map { entry ->
            WaterEntryDataModel(
                id = entry.id,
                type = entry.type,
                amount = entry.amount
            )
        }

        return WaterDataModel(
            id = waterModel.id,
            date = waterModel.date,
            waterEntries = waterEntriesData
        )
    }
}