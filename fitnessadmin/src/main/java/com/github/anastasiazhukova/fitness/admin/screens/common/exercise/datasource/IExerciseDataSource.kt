package com.github.anastasiazhukova.fitness.admin.screens.common.exercise.datasource

import com.github.anastasiazhukova.fitness.admin.screens.common.exercise.domain.ExerciseModel

interface IExerciseDataSource {
    suspend fun getAll(): List<ExerciseModel>
    suspend fun add(exerciseModel: ExerciseModel)
    suspend fun update(exerciseModel: ExerciseModel)
    suspend fun delete(exerciseModel: ExerciseModel)
}