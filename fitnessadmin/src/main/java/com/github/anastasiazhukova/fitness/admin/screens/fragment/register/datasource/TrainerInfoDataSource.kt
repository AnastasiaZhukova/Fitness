package com.github.anastasiazhukova.fitness.admin.screens.fragment.register.datasource

import com.github.anastasiazhukova.fitness.datasource.trainer.ITrainerInfoDao
import com.github.anastasiazhukova.fitness.datasource.trainer.TrainerInfoDataModel
import com.github.anastasiazhukova.fitness.admin.screens.fragment.register.domain.TrainerInfo
import com.github.anastasiazhukova.fitness.utils.IMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrainerInfoDataSource(
    private val trainerInfoDao: ITrainerInfoDao,
    private val mapper: IMapper<TrainerInfo, TrainerInfoDataModel>
) : ITrainerInfoDataSource {
    override suspend fun add(userInfo: TrainerInfo) {
        withContext(Dispatchers.IO) {
            trainerInfoDao.add(mapper.invoke(userInfo))
        }
    }
}