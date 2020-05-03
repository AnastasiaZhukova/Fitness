package com.github.anastasiazhukova.fitness.datasource.user.calories

import com.github.anastasiazhukova.fitness.datasource.user.calories.CaloriesDaoConstants.DATE
import com.github.anastasiazhukova.fitness.datasource.Firestore.USERS_COLLECTION
import com.github.anastasiazhukova.fitness.datasource.Firestore.USER_CALORIES_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseCaloriesDao : ICaloriesDao {

    private val firestore: FirebaseFirestore by lazy { Firebase.firestore }

    override suspend fun add(userId: String, model: CaloriesDataModel) = update(userId, model)

    override suspend fun getAll(userId: String): List<CaloriesDataModel> =
        firestore
            .collection(USERS_COLLECTION)
            .document(userId)
            .collection(USER_CALORIES_COLLECTION)
            .get()
            .await()
            .toObjects(CaloriesDataModel::class.java)

    override suspend fun get(userId: String, date: Long): CaloriesDataModel? =
        firestore
            .collection(USERS_COLLECTION)
            .document(userId)
            .collection(USER_CALORIES_COLLECTION)
            .whereEqualTo(DATE, date)
            .get()
            .await()
            .firstOrNull()
            ?.toObject(CaloriesDataModel::class.java)

    override suspend fun update(userId: String, model: CaloriesDataModel) {
        firestore
            .collection(USERS_COLLECTION)
            .document(userId)
            .collection(USER_CALORIES_COLLECTION)
            .document(model.id)
            .set(model)
    }

    override suspend fun delete(userId: String, entryId: String) {
        firestore
            .collection(USERS_COLLECTION)
            .document(userId)
            .collection(USER_CALORIES_COLLECTION)
            .document(entryId)
            .delete()
    }
}