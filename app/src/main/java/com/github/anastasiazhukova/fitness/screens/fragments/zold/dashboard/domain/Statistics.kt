package com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain

sealed class Statistics {
    object ToBeSet : Statistics()
    data class Value(val value: String?) : Statistics()
}