package com.github.anastasiazhukova.fitness.screens.activities.main.datasource.userInfo

import com.github.anastasiazhukova.fitness.authentication.user.IUserIdHolder
import com.github.anastasiazhukova.fitness.datasource.user.info.IUserInfoDao

class UserInfoDataSource(
    private val userIdHolder: IUserIdHolder,
    private val userInfoDao: IUserInfoDao
) : IUserInfoDataSource {

    override suspend fun updateWeight(weight: Float) {
        userIdHolder.getCurrentUserId()?.let {userId ->
            val dataModel = userInfoDao.get(userId)
            val newParams = dataModel?.userParams?.copy(
                weight = weight
            )

            if (dataModel != null && newParams != null) {
                val newDataModel = dataModel.copy(
                    userParams = newParams
                )

                userInfoDao.update(newDataModel)
            }
        }
    }
}