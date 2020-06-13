package com.github.anastasiazhukova.fitness.screens.fragments.calories.datasource

import android.util.Log
import com.github.anastasiazhukova.fitness.authentication.user.IUserIdHolder
import com.github.anastasiazhukova.fitness.datasource.user.calories.CaloriesDataModel
import com.github.anastasiazhukova.fitness.datasource.user.calories.ICaloriesDao
import com.github.anastasiazhukova.fitness.domain.calories.CaloriesEntry
import com.github.anastasiazhukova.fitness.domain.calories.CaloriesModel
import com.github.anastasiazhukova.fitness.utils.IMapper
import com.github.anastasiazhukova.fitness.utils.extensions.generateId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CaloriesDataSource(
    private val userIdHolder: IUserIdHolder,
    private val caloriesDao: ICaloriesDao,
    private val caloriesDataModelMapper: IMapper<CaloriesDataModel, CaloriesModel?>,
    private val caloriesModelDataMapper: IMapper<CaloriesModel, CaloriesDataModel>
) : ICaloriesDataSource {

    val TAG = "DebugTag CaloriesDataSource";

    override suspend fun get(date: Long): CaloriesModel? =
        withContext(Dispatchers.IO) {
            userIdHolder.getCurrentUserId()?.let { userId ->
                val dataModel = caloriesDao.get(userId, date)

                Log.d(TAG, "get: dataModel = $dataModel")

                return@withContext if (dataModel == null) {
                    CaloriesModel(
                        generateId(),
                        date,
                        emptyList()
                    )
                } else {
                    caloriesDataModelMapper.invoke(dataModel)
                }
            }

            null
        }


    override suspend fun add(caloriesModel: CaloriesModel, caloriesEntry: CaloriesEntry): CaloriesModel =
        withContext(Dispatchers.IO) {
            userIdHolder.getCurrentUserId()?.let { userId ->
                val newEntry = if (caloriesEntry.id.isEmpty()) {
                    caloriesEntry.copy(
                        id = generateId()
                    )
                } else {
                    caloriesEntry
                }

                val newEntries = caloriesModel.entries.toMutableList().apply { add(newEntry) }
                val newModel = caloriesModel.copy(entries = newEntries)
                val mappedModel = caloriesModelDataMapper.invoke(newModel)

                caloriesDao.update(userId, mappedModel)

                return@withContext newModel
            }

            caloriesModel
        }


    override suspend fun delete(caloriesModel: CaloriesModel, caloriesEntry: CaloriesEntry): CaloriesModel =
        withContext(Dispatchers.IO) {
            userIdHolder.getCurrentUserId()?.let { userId ->
                val newEntries =
                    caloriesModel.entries.toMutableList().apply { removeIf { it.id == caloriesEntry.id } }

                Log.d(
                    TAG,
                    "delete: newEntries = ${newEntries.count()} oldEntries = ${caloriesModel.entries.count()}"
                )

                val newModel = caloriesModel.copy(entries = newEntries)
                val mappedModel = caloriesModelDataMapper.invoke(newModel)

                caloriesDao.update(userId, mappedModel)

                return@withContext newModel
            }

            caloriesModel
        }

}