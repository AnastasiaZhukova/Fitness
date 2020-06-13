package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.datasource.clients

import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain.ClientModel
import com.github.anastasiazhukova.fitness.datasource.user.info.IUserInfoDao
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClientsDataSource(
    private val userInfoDao: IUserInfoDao,
    private val clientDataModelMapper: IMapper<UserInfoDataModel, ClientModel>
) : IClientsDataSource {

    override suspend fun get(trainerNickname: String): List<ClientModel> =
        withContext(Dispatchers.IO) {
            userInfoDao.getByTrainerNickname(trainerNickname).let { dataModel ->
                return@withContext dataModel.map { entry -> clientDataModelMapper.invoke(entry) }
            }
        }
}