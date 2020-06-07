package com.github.anastasiazhukova.fitness.datasource.chat

import com.github.anastasiazhukova.fitness.datasource.Firestore.CHAT_COLLECTION
import com.github.anastasiazhukova.fitness.datasource.Firestore.MESSAGES_COLLECTION
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseChatDao : IChatDao {

    private val firestore: FirebaseFirestore by lazy { Firebase.firestore }

    override fun getMessages(chatId: String): CollectionReference {
        return firestore
            .collection(CHAT_COLLECTION)
            .document(MESSAGES_COLLECTION)
            .collection(chatId)
    }
}
