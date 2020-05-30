package com.github.anastasiazhukova.fitness.datasource.trainer

import com.github.anastasiazhukova.fitness.datasource.Firestore.TRAINERS_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseTrainerInfoDao : ITrainerInfoDao {

    private val firestore: FirebaseFirestore by lazy { Firebase.firestore }

    override suspend fun add(model: TrainerInfoDataModel) = update(model)

    override suspend fun get(trainerId: String): TrainerInfoDataModel? =
        firestore
            .collection(TRAINERS_COLLECTION)
            .document(trainerId)
            .get()
            .await()
            ?.toObject(TrainerInfoDataModel::class.java)

    override suspend fun update(model: TrainerInfoDataModel) {
        firestore
            .collection(TRAINERS_COLLECTION)
            .document(model.id)
            .set(model)
    }
    override suspend fun delete(trainerId: String) {
        firestore
            .collection(TRAINERS_COLLECTION)
            .document(trainerId)
            .delete()
    }
}