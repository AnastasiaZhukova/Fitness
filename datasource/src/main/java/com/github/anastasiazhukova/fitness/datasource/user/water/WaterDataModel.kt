package com.github.anastasiazhukova.fitness.datasource.user.water

import com.github.anastasiazhukova.fitness.datasource.user.water.WaterDaoConstants.AMOUNT
import com.github.anastasiazhukova.fitness.datasource.user.water.WaterDaoConstants.DATE
import com.github.anastasiazhukova.fitness.datasource.user.water.WaterDaoConstants.ID
import com.github.anastasiazhukova.fitness.datasource.user.water.WaterDaoConstants.TYPE
import com.github.anastasiazhukova.fitness.datasource.user.water.WaterDaoConstants.WATER_ENTRIES
import com.github.anastasiazhukova.fitness.datasource.user.water.WaterDaoConstants.WATER_ENTRY_ID
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY
import com.google.gson.annotations.SerializedName

data class WaterDataModel(
    @SerializedName(ID)
    val id: String = EMPTY,

    @SerializedName(DATE)
    val date: Long? = null,

    @SerializedName(WATER_ENTRIES)
    val waterEntries: List<WaterEntryDataModel> = emptyList()
)

data class WaterEntryDataModel(
    @SerializedName(WATER_ENTRY_ID)
    val id: String = EMPTY,

    @SerializedName(TYPE)
    val type: String? = null,

    @SerializedName(AMOUNT)
    val amount: Int? = -1
)