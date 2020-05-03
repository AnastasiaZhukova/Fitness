package com.github.anastasiazhukova.fitness.screens.fragments.calories.domain

import com.github.anastasiazhukova.fitness.datasource.user.calories.CaloriesDataModel
import com.github.anastasiazhukova.fitness.datasource.user.calories.CaloriesEntryDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper

class CaloriesModelDataMapper : IMapper<CaloriesModel, CaloriesDataModel> {
    override fun invoke(caloriesModel: CaloriesModel): CaloriesDataModel {
        val caloriesEntriesData = caloriesModel.entries.map { entry ->
            CaloriesEntryDataModel(
                id = entry.id,
                name = entry.name,
                calories = entry.calories,
                weight = entry.weight
            )
        }

        return CaloriesDataModel(
            id = caloriesModel.id,
            date = caloriesModel.date,
            caloriesEntries = caloriesEntriesData
        )
    }
}