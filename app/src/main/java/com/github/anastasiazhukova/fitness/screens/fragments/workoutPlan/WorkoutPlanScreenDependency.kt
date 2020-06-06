package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan

import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanDataModelMapper
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.datasource.IWorkoutPlanDataSource
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.datasource.WorkoutPlanDataSource
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.presentation.WorkoutPlanFragment
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.usecase.IWorkoutPlanUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.usecase.WorkoutPlanUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.viewmodel.WorkoutPlanViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object WorkoutPlanScreenDependency {
    val module = module {
        scope<WorkoutPlanFragment> {
            scoped<IWorkoutPlanDataSource> {
                WorkoutPlanDataSource(
                    userIdHolder = get(),
                    workoutPlanDao = get(),
                    workoutPlanDataModelMapper = WorkoutPlanDataModelMapper()
                )
            }

            scoped<IWorkoutPlanUseCase> {
                WorkoutPlanUseCase(
                    workoutPlanDataSource = get()
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