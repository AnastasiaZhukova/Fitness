package com.github.anastasiazhukova.fitness.screens.activities.main.datasource

import android.util.Log
import com.github.anastasiazhukova.fitness.authentication.user.IUserIdHolder
import com.github.anastasiazhukova.fitness.datasource.user.weight.IWeightDao
import com.github.anastasiazhukova.fitness.datasource.user.weight.WeightDataModel
import com.github.anastasiazhukova.fitness.domain.weight.WeightModel
import com.github.anastasiazhukova.fitness.utils.IMapper
import com.github.anastasiazhukova.fitness.utils.extensions.generateId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeightDataSource(
    private val userIdHolder: IUserIdHolder,
    private val weightDao: IWeightDao,
    private val weightDataModelMapper: IMapper<WeightDataModel, WeightModel?>,
    private val weightModelDataMapper: IMapper<WeightModel, WeightDataModel>
) : IWeightDataSource {

    val TAG = "DebugTag WeightDataSource";

    override suspend fun get(date: Long): WeightModel? =
        withContext(Dispatchers.IO) {
            userIdHolder.getCurrentUserId()?.let { userId ->
                val dataModel = weightDao.get(userId, date)

                Log.d(TAG, "get: dataModel = $dataModel")

                return@withContext if (dataModel == null) {
                    WeightModel(
                        generateId(),
                        date,
                        0.0f
                    )
                } else {
                    weightDataModelMapper.invoke(dataModel)
                }
            }

            null
        }


    override suspend fun add(weightModel: WeightModel): WeightModel =
        withContext(Dispatchers.IO) {
            userIdHolder.getCurrentUserId()?.let { userId ->
                val mappedModel = weightModelDataMapper.invoke(weightModel)
                weightDao.update(userId, mappedModel)

                return@withContext weightModel
            }

            weightModel
        }
}