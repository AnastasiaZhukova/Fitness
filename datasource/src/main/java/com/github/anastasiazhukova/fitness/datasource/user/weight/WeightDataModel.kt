package com.github.anastasiazhukova.fitness.datasource.user.weight

import com.github.anastasiazhukova.fitness.datasource.user.weight.WeightDaoConstants.DATE
import com.github.anastasiazhukova.fitness.datasource.user.weight.WeightDaoConstants.ID
import com.github.anastasiazhukova.fitness.datasource.user.weight.WeightDaoConstants.VALUE
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY
import com.google.gson.annotations.SerializedName

data class WeightDataModel(
    @SerializedName(ID)
    val id: String = EMPTY,

    @SerializedName(DATE)
    val date: Long? = null,

    @SerializedName(VALUE)
    val weight: Float? = null
)