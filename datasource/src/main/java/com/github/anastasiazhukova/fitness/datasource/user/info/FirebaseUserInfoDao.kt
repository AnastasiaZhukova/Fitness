package com.github.anastasiazhukova.fitness.datasource.user.info

import com.github.anastasiazhukova.fitness.datasource.Firestore.USERS_COLLECTION
import com.github.anastasiazhukova.fitness.datasource.user.info.UserInfoDaoConstants.TRAINER_NICKNAME
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseUserInfoDao : IUserInfoDao {

    private val firestore: FirebaseFirestore by lazy { Firebase.firestore }

    override suspend fun add(model: UserInfoDataModel) = update(model)

    override suspend fun get(userId: String): UserInfoDataModel? =
        firestore
            .collection(USERS_COLLECTION)
            .document(userId)
            .get()
            .await()
            ?.toObject(UserInfoDataModel::class.java)

    override suspend fun getByTrainerNickname(trainerNickname: String): List<UserInfoDataModel> =
        firestore
            .collection(USERS_COLLECTION)
            .whereEqualTo(TRAINER_NICKNAME, trainerNickname)
            .get()
            .await()
            .toObjects(UserInfoDataModel::class.java)

    override suspend fun update(model: UserInfoDataModel) {
        firestore
            .collection(USERS_COLLECTION)
            .document(model.id)
            .set(model)
    }

    override suspend fun delete(userId: String) {
        firestore
            .collection(USERS_COLLECTION)
            .document(userId)
            .delete()
    }
}