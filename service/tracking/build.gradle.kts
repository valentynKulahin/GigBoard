plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.gigboard.service.tracking"

    compileSdk = 36
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:data"))
}