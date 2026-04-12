plugins {
    alias(libs.plugins.gigboard.android.feature)
}

android {
    namespace = "com.gigboard.feature.settings"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:datastore"))
    implementation(project(":service:tracking"))
}