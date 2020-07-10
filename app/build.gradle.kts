plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(rootProject.ext["compileSdkVersion"] as Int)

    defaultConfig {
        applicationId = "com.walfud.walle"
        minSdkVersion(rootProject.ext["minSdkVersion"] as Int)
        targetSdkVersion(rootProject.ext["targetSdkVersion"] as Int)
        versionCode = rootProject.ext["versionCode"] as Int
        versionName = rootProject.ext["versionName"] as String
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(":library"))
}