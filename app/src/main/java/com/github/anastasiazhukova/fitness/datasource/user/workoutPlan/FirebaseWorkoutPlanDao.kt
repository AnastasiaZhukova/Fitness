package com.github.anastasiazhukova.fitness.datasource.user.workoutPlan

import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDaoConstants.DATE
import com.github.anastasiazhukova.fitness.datasource.Firestore.USERS_COLLECTION
import com.github.anastasiazhukova.fitness.datasource.Firestore.USER_WORKOUT_PLAN_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseWorkoutPlanDao : IWorkoutPlanDao {

    private val firestore: FirebaseFirestore by lazy { Firebase.firestore }

    override suspend fun add(userId: String, model: WorkoutPlanDataModel) = update(userId, model)

    override suspend fun getAll(userId: String): List<WorkoutPlanDataModel> =
        firestore
            .collection(USERS_COLLECTION)
            .document(userId)
            .collection(USER_WORKOUT_PLAN_COLLECTION)
            .get()
            .await()
            .toObjects(WorkoutPlanDataModel::class.java)

    override suspend fun get(userId: String, date: Long): WorkoutPlanDataModel? =
        firestore
            .collection(USERS_COLLECTION)
            .document(userId)
            .collection(USER_WORKOUT_PLAN_COLLECTION)
            .whereEqualTo(DATE, date)
            .get()
            .await()
            .firstOrNull()
            ?.toObject(WorkoutPlanDataModel::class.java)

    override suspend fun update(userId: String, model: WorkoutPlanDataModel) {
        firestore
            .collection(USERS_COLLECTION)
            .document(userId)
            .collection(USER_WORKOUT_PLAN_COLLECTION)
            .document(model.id)
            .set(model)
    }

    override suspend fun delete(userId: String, entryId: String) {
        firestore
            .collection(USERS_COLLECTION)
            .document(userId)
            .collection(USER_WORKOUT_PLAN_COLLECTION)
            .document(entryId)
            .delete()
    }
}