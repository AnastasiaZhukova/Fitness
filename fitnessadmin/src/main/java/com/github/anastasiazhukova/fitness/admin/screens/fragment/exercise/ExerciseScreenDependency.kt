package com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise

import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.datasource.ExerciseDataSource
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.datasource.IExerciseDataSource
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.domain.ExerciseDataModelMapper
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.domain.ExerciseModelDataMapper
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.presentation.ExerciseFragment
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.usecase.ExercisesUseCase
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.usecase.IExercisesUseCase
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.viewmodel.ExercisesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ExerciseScreenDependency {
    val module = module {
        scope<ExerciseFragment> {
            scoped<IExerciseDataSource> {
                ExerciseDataSource(
                    userIdHolder = get(),
                    exerciseDao = get(),
                    exerciseDataModelMapper = ExerciseDataModelMapper(),
                    exerciseModelDataMapper = ExerciseModelDataMapper()
                )
            }

            scoped<IExercisesUseCase> {
                ExercisesUseCase(
                    exerciseDataSource = get()
                )
            }

            viewModel {
                ExercisesViewModel(
                    exercisesUseCase = get()
                )
            }
        }
    }
}