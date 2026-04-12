plugins {
    alias(libs.plugins.gigboard.android.feature)
}

android {
    namespace = "com.gigboard.feature.trips"
}

// Зависимости на :core:model, :core:designsystem, :core:common, :core:data
// уже подключены через AndroidFeatureConventionPlugin.
// Здесь добавляй только специфичные для этого feature зависимости.
dependencies {
    // пример: если home нужен доступ к БД напрямую
    implementation(project(":core:domain"))
    implementation(project(":core:maps"))
    implementation(project(":core:designsystem"))
}
