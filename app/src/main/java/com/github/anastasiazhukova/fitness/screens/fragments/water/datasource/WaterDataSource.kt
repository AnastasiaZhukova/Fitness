package com.github.anastasiazhukova.fitness.screens.fragments.water.datasource

import android.util.Log
import com.github.anastasiazhukova.fitness.authentication.user.IUserIdHolder
import com.github.anastasiazhukova.fitness.datasource.user.water.IWaterDao
import com.github.anastasiazhukova.fitness.datasource.user.water.WaterDataModel
import com.github.anastasiazhukova.fitness.domain.water.WaterEntry
import com.github.anastasiazhukova.fitness.domain.water.WaterModel
import com.github.anastasiazhukova.fitness.utils.IMapper
import com.github.anastasiazhukova.fitness.utils.extensions.generateId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WaterDataSource(
    private val userIdHolder: IUserIdHolder,
    private val waterDao: IWaterDao,
    private val waterDataModelMapper: IMapper<WaterDataModel, WaterModel?>,
    private val waterModelDataMapper: IMapper<WaterModel, WaterDataModel>
) : IWaterDataSource {

    val TAG = "DebugTag WaterDataSource";

    override suspend fun get(date: Long): WaterModel? =
        withContext(Dispatchers.IO) {
            userIdHolder.getCurrentUserId()?.let { userId ->
                val dataModel = waterDao.get(userId, date)

                Log.d(TAG, "get: dataModel = $dataModel")

                return@withContext if (dataModel == null) {
                    WaterModel(
                        generateId(),
                        date,
                        emptyList()
                    )
                } else {
                    waterDataModelMapper.invoke(dataModel)
                }
            }

            null
        }


    override suspend fun add(waterModel: WaterModel, waterEntry: WaterEntry): WaterModel =
        withContext(Dispatchers.IO) {
            userIdHolder.getCurrentUserId()?.let { userId ->
                val newEntry = if (waterEntry.id.isEmpty()) {
                    waterEntry.copy(
                        id = generateId()
                    )
                } else {
                    waterEntry
                }

                val newEntries = waterModel.entries.toMutableList().apply { add(newEntry) }
                val newModel = waterModel.copy(entries = newEntries)
                val mappedModel = waterModelDataMapper.invoke(newModel)

                waterDao.update(userId, mappedModel)

                return@withContext newModel
            }

            waterModel
        }


    override suspend fun delete(waterModel: WaterModel, waterEntry: WaterEntry): WaterModel =
        withContext(Dispatchers.IO) {
            userIdHolder.getCurrentUserId()?.let { userId ->
                val newEntries =
                    waterModel.entries.toMutableList().apply { removeIf { it.id == waterEntry.id } }

                Log.d(
                    TAG,
                    "delete: newEntries = ${newEntries.count()} oldEntries = ${waterModel.entries.count()}"
                )

                val newModel = waterModel.copy(entries = newEntries)
                val mappedModel = waterModelDataMapper.invoke(newModel)

                waterDao.update(userId, mappedModel)

                return@withContext newModel
            }

            waterModel
        }

}