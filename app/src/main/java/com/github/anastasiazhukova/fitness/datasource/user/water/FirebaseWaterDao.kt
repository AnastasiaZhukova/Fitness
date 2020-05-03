package com.github.anastasiazhukova.fitness.datasource.user.water

import com.github.anastasiazhukova.fitness.datasource.user.calories.CaloriesDaoConstants.DATE
import com.github.anastasiazhukova.fitness.datasource.Firestore.USERS_COLLECTION
import com.github.anastasiazhukova.fitness.datasource.Firestore.USER_WATER_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseWaterDao : IWaterDao {

    private val firestore: FirebaseFirestore by lazy { Firebase.firestore }

    override suspend fun add(userId: String, model: WaterDataModel) = update(userId, model)

    override suspend fun getAll(userId: String): List<WaterDataModel> =
        firestore
            .collection(USERS_COLLECTION)
            .document(userId)
            .collection(USER_WATER_COLLECTION)
            .get()
            .await()
            .toObjects(WaterDataModel::class.java)

    override suspend fun get(userId: String, date: Long): WaterDataModel? =
        firestore
            .collection(USERS_COLLECTION)
            .document(userId)
            .collection(USER_WATER_COLLECTION)
            .whereEqualTo(DATE, date)
            .get()
            .await()
            .firstOrNull()
            ?.toObject(WaterDataModel::class.java)

    override suspend fun update(userId: String, model: WaterDataModel) {
        firestore
            .collection(USERS_COLLECTION)
            .document(userId)
            .collection(USER_WATER_COLLECTION)
            .document(model.id)
            .set(model)
    }

    override suspend fun delete(userId: String, entryId: String) {
        firestore
            .collection(USERS_COLLECTION)
            .document(userId)
            .collection(USER_WATER_COLLECTION)
            .document(entryId)
            .delete()
    }
}