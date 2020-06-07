package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan

import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanDataModelMapper
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.datasource.trainerInfo.ITrainerInfoDataSource
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.datasource.trainerInfo.TrainerInfoDataSource
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.datasource.workoutPlan.IWorkoutPlanDataSource
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.datasource.workoutPlan.WorkoutPlanDataSource
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.presentation.WorkoutPlanFragment
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.usecase.trainerInfo.ITrainerInfoUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.usecase.trainerInfo.TrainerInfoUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.usecase.workoutPlan.IWorkoutPlanUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.usecase.workoutPlan.WorkoutPlanUseCase
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

            scoped<ITrainerInfoDataSource> {
                TrainerInfoDataSource(
                    userIdHolder = get(),
                    userInfoDao = get()
                )
            }

            scoped<IWorkoutPlanUseCase> {
                WorkoutPlanUseCase(
                    workoutPlanDataSource = get()
                )
            }

            scoped<ITrainerInfoUseCase> {
                TrainerInfoUseCase(
                    trainerInfoDataSource = get()
                )
            }

            viewModel {
                WorkoutPlanViewModel(
                    userIdHolder = get(),
                    workoutPlanUseCase = get(),
                    trainerInfoUseCase = get()
                )
            }
        }
    }
}