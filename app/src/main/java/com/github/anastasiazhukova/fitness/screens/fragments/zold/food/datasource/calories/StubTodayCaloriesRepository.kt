package com.github.anastasiazhukova.fitness.screens.fragments.zold.food.datasource.calories

import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.domain.TodayCaloriesModel

class StubTodayCaloriesRepository : ITodayCaloriesRepository {
    override suspend fun load(): TodayCaloriesModel? {
        return TodayCaloriesModel("1200 cal")
    }

}