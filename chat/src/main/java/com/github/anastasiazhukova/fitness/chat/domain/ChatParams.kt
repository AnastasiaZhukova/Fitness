package com.github.anastasiazhukova.fitness.chat.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChatParams(
    val chatId: String,
    val chatOpponent: String
) : Parcelable