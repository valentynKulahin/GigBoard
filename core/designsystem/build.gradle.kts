plugins {
    alias(libs.plugins.gigboard.android.library)
    alias(libs.plugins.gigboard.android.compose)
}

android {
    namespace = "com.gigboard.core.designsystem"
}

dependencies {
    implementation(project(":core:model"))

    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material.icons.extended)
}
