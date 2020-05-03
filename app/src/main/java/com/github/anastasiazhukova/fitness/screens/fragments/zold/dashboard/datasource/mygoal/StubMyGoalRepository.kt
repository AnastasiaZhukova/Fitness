package com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.mygoal

import com.github.anastasiazhukova.fitness.authentication.auth.IAuthenticationManager
import com.github.anastasiazhukova.fitness.datasource.user.info.IUserInfoDao
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain.MyGoal
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.core.KoinComponent
import org.koin.core.inject

class StubMyGoalRepository : IMyGoalRepository, KoinComponent {

    private val userRepository: IUserInfoDao by inject()
    private val auth: IAuthenticationManager by inject()
    private val firestore: FirebaseFirestore by lazy { Firebase.firestore }

    override suspend fun load(): MyGoal? {
        val userId = "1"
        userId?.let {
            userRepository.get(userId)?.userGoal?.let { userGoal ->
                return MyGoal(
                    weightGoal = "${userGoal.goalWeight} kg",
                    caloriesGoal = "${userGoal.goalCalories}",
                    waterGoal = "${userGoal.goalWater}"
                )
            }
        }

        return MyGoal()
    }
}