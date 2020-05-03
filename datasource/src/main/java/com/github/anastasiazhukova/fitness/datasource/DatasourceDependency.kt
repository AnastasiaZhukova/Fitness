package com.github.anastasiazhukova.fitness.datasource

import com.github.anastasiazhukova.fitness.datasource.exercise.FirebaseExerciseDao
import com.github.anastasiazhukova.fitness.datasource.exercise.IExerciseDao
import com.github.anastasiazhukova.fitness.datasource.user.calories.FirebaseCaloriesDao
import com.github.anastasiazhukova.fitness.datasource.user.calories.ICaloriesDao
import com.github.anastasiazhukova.fitness.datasource.user.info.FirebaseUserInfoDao
import com.github.anastasiazhukova.fitness.datasource.user.info.IUserInfoDao
import com.github.anastasiazhukova.fitness.datasource.user.water.FirebaseWaterDao
import com.github.anastasiazhukova.fitness.datasource.user.water.IWaterDao
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.FirebaseWorkoutPlanDao
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.IWorkoutPlanDao
import org.koin.dsl.module

object DatasourceDependency {
    val module = module {
        factory<IUserInfoDao> { FirebaseUserInfoDao() }
        factory<IWaterDao> { FirebaseWaterDao() }
        factory<ICaloriesDao> { FirebaseCaloriesDao() }
        factory<IWorkoutPlanDao> { FirebaseWorkoutPlanDao() }

        factory<IExerciseDao> { FirebaseExerciseDao() }
    }
}