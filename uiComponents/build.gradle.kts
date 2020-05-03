plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
}

android {
    compileSdkVersion(AndroidVersion.compileSdk)
    buildToolsVersion(AndroidVersion.buildTools)

    defaultConfig {
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

    implementation(Libraries.kotlin)

    implementation(Libraries.materialComponents)

    implementation(Libraries.recyclerView)

    implementation(project(Module.utils))
}