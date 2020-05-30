package com.github.anastasiazhukova.fitness

import android.app.Application
import com.github.anastasiazhukova.fitness.authentication.AuthenticationDependency
import com.github.anastasiazhukova.fitness.datasource.DatasourceDependency

import com.github.anastasiazhukova.fitness.screens.activities.workout.WorkoutScreenDependency
import com.github.anastasiazhukova.fitness.screens.fragments.calories.CaloriesScreenDependency
import com.github.anastasiazhukova.fitness.screens.fragments.login.LoginScreenDependency
import com.github.anastasiazhukova.fitness.screens.fragments.register.RegisterScreenDependency
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.StatisticsScreenDependency
import com.github.anastasiazhukova.fitness.screens.fragments.water.WaterScreenDependency
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.WorkoutPlanScreenDependency
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                AuthenticationDependency.module,
                LoginScreenDependency.module,
                RegisterScreenDependency.module,

                WaterScreenDependency.module,
                CaloriesScreenDependency.module,
                WorkoutPlanScreenDependency.module,
                StatisticsScreenDependency.module,

                DatasourceDependency.module,
                WorkoutScreenDependency.module
            )
        }
    }
}