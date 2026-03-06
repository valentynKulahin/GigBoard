plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.gigboard.core.common"

    compileSdk = 36
}

dependencies {
    implementation(libs.androidx.core.ktx)
}
