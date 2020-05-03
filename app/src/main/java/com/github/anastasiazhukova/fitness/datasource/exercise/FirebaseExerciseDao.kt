package com.github.anastasiazhukova.fitness.datasource.exercise

import com.github.anastasiazhukova.fitness.datasource.Firestore.EXERCISE_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class FirebaseExerciseDao : IExerciseDao {

    private val firestore: FirebaseFirestore by lazy { Firebase.firestore }

    override suspend fun add(model: ExerciseDataModel) = update(model)

    override suspend fun getAll(): List<ExerciseDataModel> =
        firestore
            .collection(EXERCISE_COLLECTION)
            .get()
            .await()
            .toObjects(ExerciseDataModel::class.java)

    override suspend fun get(id: String): ExerciseDataModel? =
        firestore
            .collection(EXERCISE_COLLECTION)
            .document(id)
            .get()
            .await()
            ?.toObject(ExerciseDataModel::class.java)

    override suspend fun update(model: ExerciseDataModel) {
        firestore
            .collection(EXERCISE_COLLECTION)
            .document(model.id)
            .set(model)
    }

    override suspend fun delete(id: String) {
        firestore
            .collection(EXERCISE_COLLECTION)
            .document(id)
            .delete()
    }
}
