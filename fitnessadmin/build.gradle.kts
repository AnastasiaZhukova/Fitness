plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.googleGsmServices)
}

android {
    compileSdkVersion(AndroidVersion.compileSdk)
    buildToolsVersion(AndroidVersion.buildTools)

    defaultConfig {
        applicationId = AppConfig.adminApplicationId
        minSdkVersion(AndroidVersion.minSdk)
        targetSdkVersion(AndroidVersion.targetSdk)
        versionCode = AndroidVersion.versionCode
        versionName = AndroidVersion.versionName
    }

    buildTypes {
        getByName(AppConfig.release) {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(Libraries.appCompat)

    implementation(Libraries.constraintLayout)
    implementation(Libraries.countdownTimer)

    implementation(Libraries.firebaseAnalytics)
    implementation(Libraries.firebaseAuth)
    implementation(Libraries.firebaseFirestore)
    implementation(Libraries.firebaseUiAuth)

    implementation(Libraries.koinCore)
    implementation(Libraries.koinExt)
    implementation(Libraries.koinScope)
    implementation(Libraries.koinViewModel)
    implementation(Libraries.kotlin)
    implementation(Libraries.kotlinCoroutines)
    implementation(Libraries.kotlinCoroutinesAndroid)
    implementation(Libraries.kotlinCoroutinesIntegration)

    implementation(Libraries.materialComponents)
    implementation(Libraries.mpChart)

    implementation(Libraries.recyclerView)
    
    implementation(Libraries.viewModel)
}