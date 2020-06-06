package com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan

import com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.datasource.IWorkoutPlanDataSource
import com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.datasource.WorkoutPlanDataSource
import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanDataModelMapper
import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanModelDataMapper
import com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.presentation.WorkoutPlanActivity
import com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.usecase.IWorkoutPlanUseCase
import com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.usecase.WorkoutPlanUseCase
import com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.viewmodel.WorkoutPlanViewModel
import com.github.anastasiazhukova.fitness.admin.screens.common.exercise.datasource.ExerciseDataSource
import com.github.anastasiazhukova.fitness.admin.screens.common.exercise.datasource.IExerciseDataSource
import com.github.anastasiazhukova.fitness.admin.screens.common.exercise.domain.ExerciseDataModelMapper
import com.github.anastasiazhukova.fitness.admin.screens.common.exercise.domain.ExerciseModelDataMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object WorkoutPlanScreenDependency {
    val module = module {
        scope<WorkoutPlanActivity> {
            scoped<IWorkoutPlanDataSource> {
                WorkoutPlanDataSource(
                    workoutPlanDao = get(),
                    workoutPlanDataModelMapper = WorkoutPlanDataModelMapper(),
                    workoutPlanModelDataMapper = WorkoutPlanModelDataMapper()
                )
            }

            scoped<IExerciseDataSource> {
                ExerciseDataSource(
                    userIdHolder = get(),
                    exerciseDao = get(),
                    exerciseDataModelMapper = ExerciseDataModelMapper(),
                    exerciseModelDataMapper = ExerciseModelDataMapper()
                )
            }

            scoped<IWorkoutPlanUseCase> {
                WorkoutPlanUseCase(
                    workoutPlanDataSource = get(),
                    exerciseDataSource = get()
                )
            }

            viewModel {
                WorkoutPlanViewModel(
                    workoutPlanUseCase = get()
                )
            }
        }
    }
}