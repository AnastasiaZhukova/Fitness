package com.github.anastasiazhukova.fitness.authentication.auth

import com.github.anastasiazhukova.fitness.utils.Result
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthenticationManager : IAuthenticationManager {

    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun isLoggedIn(): Boolean = auth.currentUser != null

    override suspend fun login(email: String, password: String): Result<Boolean> =
        try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user

            if (user != null) {
                Result.Success(true)
            } else {
                Result.Success(false)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun register(email: String, password: String): Result<Boolean> =
        try {
            val user = auth.createUserWithEmailAndPassword(email, password).await().user

            if (user != null) {
                Result.Success(true)
            } else {
                Result.Success(false)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun logout(): Result<Boolean> =
        try {
            auth.signOut()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
}