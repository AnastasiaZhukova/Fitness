package com.github.anastasiazhukova.fitness.datasource.user.calories

import com.github.anastasiazhukova.fitness.datasource.user.calories.CaloriesDaoConstants.CALORIES
import com.github.anastasiazhukova.fitness.datasource.user.calories.CaloriesDaoConstants.CALORIES_ENTRIES
import com.github.anastasiazhukova.fitness.datasource.user.calories.CaloriesDaoConstants.CALORIES_ENTRY_ID
import com.github.anastasiazhukova.fitness.datasource.user.calories.CaloriesDaoConstants.DATE
import com.github.anastasiazhukova.fitness.datasource.user.calories.CaloriesDaoConstants.ID
import com.github.anastasiazhukova.fitness.datasource.user.calories.CaloriesDaoConstants.NAME
import com.github.anastasiazhukova.fitness.datasource.user.calories.CaloriesDaoConstants.WEIGHT
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY
import com.google.gson.annotations.SerializedName

data class CaloriesDataModel(
    @SerializedName(ID)
    val id: String = EMPTY,

    @SerializedName(DATE)
    val date: Long? = null,

    @SerializedName(CALORIES_ENTRIES)
    val caloriesEntries: List<CaloriesEntryDataModel> = emptyList()
)

data class CaloriesEntryDataModel(
    @SerializedName(CALORIES_ENTRY_ID)
    val id: String = EMPTY,

    @SerializedName(NAME)
    val name: String? = null,

    @SerializedName(CALORIES)
    val calories: Int? = -1,

    @SerializedName(WEIGHT)
    val weight: Int? = -1
)