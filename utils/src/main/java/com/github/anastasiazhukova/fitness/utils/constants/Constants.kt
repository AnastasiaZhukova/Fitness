package com.github.anastasiazhukova.fitness.utils.constants

object Constants {
    object String {
        const val EMPTY = ""
    }

    object Time {
        const val SECONDS_5_IN_MILLIS = 5000L
        const val MILLIS_IN_SECOND = 1000L
        const val DAY_IN_MILLIS = 86_400_000L
    }

    object Extra {
        const val ACTIVITY_EXTRA = "ACTIVITY_EXTRA"
        const val FRAGMENT_EXTRA = "FRAGMENT_EXTRA"
    }

    object Authentication {
        const val MIN_PASSWORD_LENGTH = 6
    }

    object FitnessLevel {
        const val LIGHT = 1
        const val MEDIUM = 2
        const val HARD = 3
    }
}