package com.github.anastasiazhukova.statistics.datasource.weight

import com.github.anastasiazhukova.fitness.datasource.user.weight.IWeightDao
import com.github.anastasiazhukova.statistics.domain.StatisticsEntry

class WeightStatisticsRepository(
    private val weightDao: IWeightDao
) : IWeightStatisticsRepository {

    override suspend fun load(userId: String): List<StatisticsEntry> =
        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
            return@withContext weightDao.getAll(userId).mapNotNull { dataModel ->
                val date = dataModel.date
                val weight = dataModel.weight

                if (date != null && date > 0 && weight != null && weight > 0.0f) {
                    StatisticsEntry.Weight(
                        date,
                        weight
                    )
                } else {
                    null
                }
            }.sortedBy {
                it.date
            }
        }
}