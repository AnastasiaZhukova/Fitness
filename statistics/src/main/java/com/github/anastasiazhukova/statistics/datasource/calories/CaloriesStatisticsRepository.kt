package com.github.anastasiazhukova.statistics.datasource.calories

import com.github.anastasiazhukova.fitness.datasource.user.calories.CaloriesDataModel
import com.github.anastasiazhukova.fitness.datasource.user.calories.ICaloriesDao
import com.github.anastasiazhukova.fitness.domain.calories.CaloriesModel
import com.github.anastasiazhukova.fitness.utils.IMapper
import com.github.anastasiazhukova.fitness.utils.extensions.calculateCalories
import com.github.anastasiazhukova.statistics.domain.StatisticsEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CaloriesStatisticsRepository(
    private val caloriesDao: ICaloriesDao,
    private val caloriesDataModelMapper: IMapper<CaloriesDataModel, CaloriesModel?>
) : ICaloriesStatisticsRepository {

    override suspend fun load(userId: String): List<StatisticsEntry> =
        withContext(Dispatchers.IO) {
            return@withContext caloriesDao.getAll(userId).mapNotNull { dataModel ->
                val date = dataModel.date
                val model = caloriesDataModelMapper.invoke(dataModel)

                if (date != null && date > 0 && model != null) {
                    var sum = 0
                    dataModel.caloriesEntries.forEach { entry ->
                        val weight = entry.weight ?: 0
                        val calories = entry.calories ?: 0

                        if (weight > 0 && calories > 0) {
                            val cal = calculateCalories(weight, calories)
                            sum += cal
                        }
                    }

                    StatisticsEntry.Calories(date, sum.toFloat(), model)
                } else {
                    null
                }
            }.sortedBy {
                it.date
            }
        }
}