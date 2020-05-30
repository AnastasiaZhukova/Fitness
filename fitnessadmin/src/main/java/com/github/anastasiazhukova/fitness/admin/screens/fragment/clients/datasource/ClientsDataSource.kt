package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.datasource

import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain.ClientModel
import com.github.anastasiazhukova.fitness.authentication.user.IUserIdHolder
import com.github.anastasiazhukova.fitness.datasource.user.info.IUserInfoDao
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClientsDataSource(
    private val userIdHolder: IUserIdHolder,
    private val userInfoDao: IUserInfoDao,
    private val clientDataModelMapper: IMapper<UserInfoDataModel, ClientModel>
) : IClientsDataSource {

    override suspend fun get(): List<ClientModel> =
        withContext(Dispatchers.IO) {
            //userIdHolder.getCurrentUserId()?.let { trainerId ->
            "123".let { trainerId ->
                userInfoDao.getByTrainerId(trainerId).let { dataModel ->
                    return@withContext dataModel.map { entry -> clientDataModelMapper.invoke(entry) }
                }
            }

            emptyList<ClientModel>()
        }
}