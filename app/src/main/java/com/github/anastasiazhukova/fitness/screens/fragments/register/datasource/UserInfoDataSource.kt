package com.github.anastasiazhukova.fitness.screens.fragments.register.datasource

import com.github.anastasiazhukova.fitness.datasource.user.info.IUserInfoDao
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDataModel
import com.github.anastasiazhukova.fitness.screens.fragments.register.domain.UserInfo
import com.github.anastasiazhukova.fitness.utils.IMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserInfoDataSource(
    private val userInfoDao: IUserInfoDao,
    private val mapper: IMapper<UserInfo, UserInfoDataModel>
) : IUserInfoDataSource {
    override suspend fun add(userInfo: UserInfo) {
        withContext(Dispatchers.IO) {
            userInfoDao.add(mapper.invoke(userInfo))
        }
    }
}