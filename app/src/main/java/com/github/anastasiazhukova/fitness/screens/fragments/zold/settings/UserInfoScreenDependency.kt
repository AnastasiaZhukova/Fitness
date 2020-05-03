package com.github.anastasiazhukova.fitness.screens.fragments.zold.settings

import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.datasource.calories.ITodayCaloriesRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.datasource.calories.StubTodayCaloriesRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.datasource.water.ITodayWaterRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.datasource.water.StubTodayWaterRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.presentation.FoodFragment
import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.usecase.ITodayStatisticsUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.usecase.TodayStatisticsUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.viewmodel.FoodViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object UserInfoScreenDependency {
    val module = module {
        scope(named<FoodFragment>()) {
            factory<ITodayCaloriesRepository> { StubTodayCaloriesRepository() }
            factory<ITodayWaterRepository> { StubTodayWaterRepository() }

            factory<ITodayStatisticsUseCase> {
                TodayStatisticsUseCase(
                    todayCaloriesRepository = get(),
                    todayWaterRepository = get()
                )
            }

            viewModel { FoodViewModel(get()) }
        }
    }
}
