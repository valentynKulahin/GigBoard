plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.gigboard.core.network"

    compileSdk = 36
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.androidx.core.ktx)
}
