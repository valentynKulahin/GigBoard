plugins {
    alias(libs.plugins.gigboard.android.application)
    alias(libs.plugins.gigboard.android.compose)
    alias(libs.plugins.gigboard.android.hilt)
}

android {
    namespace = "com.gigboard.app"

    defaultConfig {
        applicationId = "com.gigboard.app"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))

    implementation(project(":feature:home"))
    implementation(project(":feature:trips"))
    implementation(project(":feature:map"))
    implementation(project(":feature:settings"))

    implementation(project(":service:tracking"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.navigation.compose)
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.hilt.navigation)
}
