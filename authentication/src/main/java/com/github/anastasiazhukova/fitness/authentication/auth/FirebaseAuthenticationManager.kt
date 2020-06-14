package com.github.anastasiazhukova.fitness.authentication.auth

import com.github.anastasiazhukova.fitness.utils.Result
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthenticationManager : IAuthenticationManager {

    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun isLoggedIn(): Boolean = auth.currentUser != null

    override suspend fun login(email: String, password: String): Result<String?> =
        try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user

            if (user != null) {
                Result.Success(user.uid)
            } else {
                Result.Success(null)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun register(email: String, password: String): Result<String?> =
        try {
            val user = auth.createUserWithEmailAndPassword(email, password).await().user

            if (user != null) {
                Result.Success(user.uid)
            } else {
                Result.Success(null)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }

    override fun logout(): Result<Boolean> =
        try {
            auth.signOut()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
}