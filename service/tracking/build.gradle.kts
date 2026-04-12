plugins {
    alias(libs.plugins.gigboard.android.library)
    alias(libs.plugins.gigboard.android.feature)
    alias(libs.plugins.gigboard.android.hilt)
}

android {
    namespace = "com.gigboard.service.tracking"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:database"))

    api(libs.play.services.location)
    api(libs.androidx.core.ktx)
}