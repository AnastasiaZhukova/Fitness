package com.github.anastasiazhukova.fitness.datasource.trainer

import com.github.anastasiazhukova.fitness.datasource.trainer.TrainerInfoDaoConstants.ID
import com.github.anastasiazhukova.fitness.datasource.trainer.TrainerInfoDaoConstants.NAME
import com.github.anastasiazhukova.fitness.datasource.trainer.TrainerInfoDaoConstants.NICKNAME
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY
import com.google.gson.annotations.SerializedName

data class TrainerInfoDataModel(
    @SerializedName(ID)
    val id: String = EMPTY,

    @SerializedName(NAME)
    val name: String? = null,

    @SerializedName(NICKNAME)
    val nickname: String? = null
)
