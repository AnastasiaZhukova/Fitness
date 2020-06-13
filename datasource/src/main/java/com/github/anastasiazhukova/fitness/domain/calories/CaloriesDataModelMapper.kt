package com.github.anastasiazhukova.fitness.domain.calories

import com.github.anastasiazhukova.fitness.datasource.user.calories.CaloriesDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper

class CaloriesDataModelMapper : IMapper<CaloriesDataModel, CaloriesModel?> {
    override fun invoke(data: CaloriesDataModel): CaloriesModel? {
        val caloriesEntries = data.caloriesEntries.mapNotNull { entry ->
            if (entry.id.isNotEmpty() && !entry.name.isNullOrEmpty() && entry.calories != null && entry.weight != null) {
                CaloriesEntry(
                    entry.id,
                    entry.name!!,
                    entry.calories!!,
                    entry.weight!!
                )
            } else {
                null
            }
        }

        return if (data.id.isNotEmpty() && data.date != null) {
            CaloriesModel(
                id = data.id,
                date = data.date!!,
                entries = caloriesEntries
            )
        } else {
            null
        }
    }
}