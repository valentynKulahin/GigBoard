plugins {
    alias(libs.plugins.gigboard.android.library)
    alias(libs.plugins.gigboard.android.hilt)
}

android {
    namespace = "com.gigboard.core.domain"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:data"))
}