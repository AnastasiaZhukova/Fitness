package com.github.anastasiazhukova.fitness.screens.fragments.zold.food.datasource.water

import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.domain.TodayWaterModel

class StubTodayWaterRepository : ITodayWaterRepository {
    override suspend fun load(): TodayWaterModel? {
        return TodayWaterModel("1000 ml")
    }
}