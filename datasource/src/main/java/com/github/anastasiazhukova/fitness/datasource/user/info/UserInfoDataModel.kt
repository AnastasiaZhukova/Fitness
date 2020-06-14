package com.github.anastasiazhukova.fitness.datasource.user.info


import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDaoConstants.BIRTHDAY
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDaoConstants.FITNESS_LEVEL
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDaoConstants.GENDER
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDaoConstants.GOAL_CALORIES
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDaoConstants.GOAL_WATER
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDaoConstants.GOAL_WEIGHT
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDaoConstants.HEIGHT
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDaoConstants.ID
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDaoConstants.NAME
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDaoConstants.TRAINER_NICKNAME
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDaoConstants.USER_GOAL
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDaoConstants.USER_PARAMS
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDaoConstants.WEIGHT
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY
import com.google.gson.annotations.SerializedName

data class UserInfoDataModel(
    @SerializedName(ID)
    val id: String = EMPTY,

    @SerializedName(NAME)
    val name: String? = null,

    @SerializedName(TRAINER_NICKNAME)
    val trainerNickname: String? = null,

    @SerializedName(BIRTHDAY)
    val birthday: Long? = null,

    @SerializedName(GENDER)
    val gender: Boolean = false,

    @SerializedName(USER_PARAMS)
    val userParams: UserParams? = null,

    @SerializedName(USER_GOAL)
    val userGoal: UserGoal? = null
)

data class UserParams(
    @SerializedName(HEIGHT)
    val height: Int = -1,

    @SerializedName(WEIGHT)
    val weight: Float = -1.0f,

    @SerializedName(FITNESS_LEVEL)
    val fitnessLevel: Int = -1
)

data class UserGoal(
    @SerializedName(GOAL_WEIGHT)
    val goalWeight: Int = -1,

    @SerializedName(GOAL_CALORIES)
    val goalCalories: Int = -1,

    @SerializedName(GOAL_WATER)
    val goalWater: Int = -1
)
