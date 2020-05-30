package com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.domain

import com.github.anastasiazhukova.fitness.datasource.exercise.ExerciseDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper

class ExerciseDataModelMapper : IMapper<ExerciseDataModel, ExerciseModel?> {
    override fun invoke(data: ExerciseDataModel): ExerciseModel? {
        return if (data.id.isNotEmpty()) {
            ExerciseModel(
                id = data.id,
                authorId = data.authorId,
                name = data.name,
                description = data.description
            )
        } else {
            null
        }
    }
}