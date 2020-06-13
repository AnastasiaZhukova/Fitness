package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.datasource.trainerInfo

import com.github.anastasiazhukova.fitness.authentication.user.IUserIdHolder
import com.github.anastasiazhukova.fitness.datasource.trainer.ITrainerInfoDao
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrainerNicknameDataSource(
    private val userIdHolder: IUserIdHolder,
    private val trainerInfoDao: ITrainerInfoDao
) : ITrainerNicknameDataSource {

    override suspend fun get(): String =
        withContext(Dispatchers.IO) {
            userIdHolder.getCurrentUserId()?.let { trainerId ->
                return@withContext trainerInfoDao.get(trainerId)?.nickname ?: EMPTY

            }

            EMPTY
        }
}