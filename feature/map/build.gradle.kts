plugins {
    alias(libs.plugins.gigboard.android.feature)
}

android {
    namespace = "com.gigboard.feature.map"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:maps"))
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))

    api(libs.maps.compose)
    api(libs.play.services.maps)
}