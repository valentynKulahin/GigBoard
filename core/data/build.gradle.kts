plugins {
    alias(libs.plugins.gigboard.android.library)
    alias(libs.plugins.gigboard.android.hilt)
}

android {
    namespace = "com.gigboard.core.data"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))
}
