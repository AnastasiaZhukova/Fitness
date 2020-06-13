package com.github.anastasiazhukova.statistics.datasource.water

import com.github.anastasiazhukova.fitness.datasource.user.water.IWaterDao
import com.github.anastasiazhukova.fitness.datasource.user.water.WaterDataModel
import com.github.anastasiazhukova.fitness.domain.water.WaterModel
import com.github.anastasiazhukova.fitness.utils.IMapper
import com.github.anastasiazhukova.statistics.domain.StatisticsEntry
import kotlinx.coroutines.withContext

class WaterStatisticsRepository(
    private val waterDao: IWaterDao,
    private val waterDataModelMapper: IMapper<WaterDataModel, WaterModel?>
) : IWaterStatisticsRepository {

    override suspend fun load(userId: String): List<StatisticsEntry> =
        withContext(kotlinx.coroutines.Dispatchers.IO) {
            return@withContext waterDao.getAll(userId).mapNotNull { dataModel ->
                val date = dataModel.date
                val model = waterDataModelMapper.invoke(dataModel)

                if (date != null && date > 0 && model != null) {
                    var sum = 0
                    dataModel.waterEntries.forEach { entry ->
                        sum += entry.amount ?: 0
                    }

                    StatisticsEntry.Water(
                        date,
                        sum.toFloat(),
                        model
                    )
                } else {
                    null
                }
            }.sortedBy {
                it.date
            }
        }
}