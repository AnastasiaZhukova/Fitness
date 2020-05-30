package com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.usecase

import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.datasource.IExerciseDataSource
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.domain.ExerciseModel
import com.github.anastasiazhukova.fitness.utils.Result

class ExercisesUseCase(
    private val exerciseDataSource: IExerciseDataSource
) : IExercisesUseCase {

    override suspend fun getAll(): Result<List<ExerciseModel>> =
        try {
            Result.Success(exerciseDataSource.getAll())
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun add(model: ExerciseModel): Result<List<ExerciseModel>> =
        try {
            exerciseDataSource.add(model)
            getAll()
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun update(model: ExerciseModel): Result<List<ExerciseModel>> =
        try {
            exerciseDataSource.update(model)
            getAll()
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun delete(model: ExerciseModel): Result<List<ExerciseModel>> =
        try {
            exerciseDataSource.delete(model)
            getAll()
        } catch (e: Exception) {
            Result.Error(e)
        }
}