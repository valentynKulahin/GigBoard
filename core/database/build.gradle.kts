plugins {
    alias(libs.plugins.gigboard.android.library)
    alias(libs.plugins.gigboard.android.hilt)
    alias(libs.plugins.gigboard.android.serialization)
}

android {
    namespace = "com.gigboard.core.database"
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}