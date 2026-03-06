plugins {
    alias(libs.plugins.gigboard.android.library)
    alias(libs.plugins.gigboard.android.hilt)
}

android {
    namespace = "com.gigboard.core.data"
}

dependencies {
    api(project(":core:model"))
    implementation(project(":core:database"))
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))
}
