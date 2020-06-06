package com.github.anastasiazhukova.fitness.admin

import android.app.Application
import com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.WorkoutPlanScreenDependency
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.ClientsScreenDependency
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.ExerciseScreenDependency
import com.github.anastasiazhukova.fitness.admin.screens.fragment.login.LoginScreenDependency
import com.github.anastasiazhukova.fitness.authentication.AuthenticationDependency
import com.github.anastasiazhukova.fitness.datasource.DatasourceDependency
import com.github.anastasiazhukova.fitness.screens.fragments.register.RegisterScreenDependency
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
                ClientsScreenDependency.module,
                DatasourceDependency.module,
                ExerciseScreenDependency.module,
                WorkoutPlanScreenDependency.module
            )
        }
    }
}