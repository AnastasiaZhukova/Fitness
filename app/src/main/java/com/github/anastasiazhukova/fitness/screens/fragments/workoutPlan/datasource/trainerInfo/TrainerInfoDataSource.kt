package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.datasource.trainerInfo

import com.github.anastasiazhukova.fitness.authentication.user.IUserIdHolder
import com.github.anastasiazhukova.fitness.datasource.user.info.IUserInfoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrainerInfoDataSource(
    private val userIdHolder: IUserIdHolder,
    private val userInfoDao: IUserInfoDao
) : ITrainerInfoDataSource {

    override suspend fun getTrainerNickname() =
        withContext(Dispatchers.IO) {
            userIdHolder.getCurrentUserId()?.let {
                return@withContext userInfoDao.get(it)?.trainerNickname
            }

            null
        }
}