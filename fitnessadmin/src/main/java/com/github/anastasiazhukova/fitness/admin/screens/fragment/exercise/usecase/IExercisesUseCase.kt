package com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.usecase

import com.github.anastasiazhukova.fitness.admin.screens.common.exercise.domain.ExerciseModel
import com.github.anastasiazhukova.fitness.utils.Result

interface IExercisesUseCase {
    suspend fun getAll(): Result<List<ExerciseModel>>
    suspend fun add(model: ExerciseModel): Result<List<ExerciseModel>>
    suspend fun update(model: ExerciseModel): Result<List<ExerciseModel>>
    suspend fun delete(model: ExerciseModel): Result<List<ExerciseModel>>
}