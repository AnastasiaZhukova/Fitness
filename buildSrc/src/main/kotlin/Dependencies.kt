object BuildPlugins {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${GradleVersion.buildTools}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val googleGsm = "com.google.gms:google-services:${Version.googleGsm}"
    const val googleGsmServices = "com.google.gms.google-services"
}

object Libraries {
    const val androidxCore = "androidx.core:core:${Version.androidxCore}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"

    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
    const val countdownTimer = "com.github.iwgang:countdownview:${Version.countdownTimer}"


    const val firebaseAnalytics = "com.google.firebase:firebase-analytics:${Version.firebaseAnalytics}"
    const val firebaseAuth = "com.google.firebase:firebase-auth:${Version.firebaseAuth}"
    const val firebaseFirestore = "com.google.firebase:firebase-firestore-ktx:${Version.firebaseFirestore}"
    const val firebaseUiAuth = "com.firebaseui:firebase-ui-auth:${Version.firebaseUiAuth}"

    const val fragment = "androidx.fragment:fragment:${Version.fragment}"

    const val glide = "com.github.bumptech.glide:glide:${Version.glide}"

    const val junit = "junit:junit:${Version.junit}"

    const val koinAndroid = "org.koin:koin-android:${Version.koin}"
    const val koinCore = "org.koin:koin-core:${Version.koin}"
    const val koinExt = "org.koin:koin-androidx-ext:${Version.koin}"
    const val koinScope = "org.koin:koin-androidx-scope:${Version.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Version.koin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.kotlinCoroutines}"
    const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.kotlinCoroutines}"
    const val kotlinCoroutinesIntegration = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Version.kotlinCoroutines}"

    const val materialComponents = "com.google.android.material:material:${Version.materialComponents}"
    const val mpChart = "com.github.PhilJay:MPAndroidChart:${Version.mpChart}"

    const val recyclerView = "androidx.recyclerview:recyclerview:${Version.recyclerview}"

    const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefresh}"

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.viewModel}"
    const val viewModelLifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.viewModel}"
}

object Module {
    const val authentication = ":authentication"
    const val chat = ":chat"
    const val datasource = ":datasource"
    const val uiComponents = ":uiComponents"
    const val utils = ":utils"
}