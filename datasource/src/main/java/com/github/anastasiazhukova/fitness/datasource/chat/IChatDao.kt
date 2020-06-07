package com.github.anastasiazhukova.fitness.datasource.chat

import com.google.firebase.firestore.CollectionReference

interface IChatDao {
    fun getMessages(chatId: String): CollectionReference
}