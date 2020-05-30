package com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.domain

import com.github.anastasiazhukova.fitness.datasource.exercise.ExerciseDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper

class ExerciseModelDataMapper : IMapper<ExerciseModel, ExerciseDataModel> {
    override fun invoke(exerciseModel: ExerciseModel): ExerciseDataModel {
        return ExerciseDataModel(
            id = exerciseModel.id,
            authorId = exerciseModel.authorId,
            name = exerciseModel.name,
            description = exerciseModel.description
        )
    }
}