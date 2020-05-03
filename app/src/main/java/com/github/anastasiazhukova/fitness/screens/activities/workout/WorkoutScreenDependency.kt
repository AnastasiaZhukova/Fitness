package com.github.anastasiazhukova.fitness.screens.activities.workout

import com.github.anastasiazhukova.fitness.screens.activities.workout.datasource.IWorkoutDataSource
import com.github.anastasiazhukova.fitness.screens.activities.workout.datasource.WorkoutDataSource
import com.github.anastasiazhukova.fitness.screens.activities.workout.presentation.WorkoutActivity
import com.github.anastasiazhukova.fitness.screens.activities.workout.usecase.IWorkoutUseCase
import com.github.anastasiazhukova.fitness.screens.activities.workout.usecase.WorkoutUseCase
import com.github.anastasiazhukova.fitness.screens.activities.workout.viewmodel.WorkoutViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object WorkoutScreenDependency {
    val module = module {
        scope<WorkoutActivity> {
            scoped<IWorkoutDataSource> {
                WorkoutDataSource(
                    exerciseDao = get()
                )
            }

            scoped<IWorkoutUseCase> {
                WorkoutUseCase(
                    workoutDataSource = get()
                )
            }

            viewModel { WorkoutViewModel(get()) }
        }
    }
}