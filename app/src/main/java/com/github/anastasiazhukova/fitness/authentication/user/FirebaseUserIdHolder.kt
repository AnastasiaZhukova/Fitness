package com.github.anastasiazhukova.fitness.authentication.user

import com.google.firebase.auth.FirebaseAuth

class FirebaseUserIdHolder : IUserIdHolder {

    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun getCurrentUserId(): String? = auth.currentUser?.uid
}