plugins {
    alias(libs.plugins.gigboard.android.feature)
}

android {
    namespace = "com.gigboard.feature.home"
}

// Зависимости на :core:model, :core:designsystem, :core:common, :core:data
// уже подключены через AndroidFeatureConventionPlugin.
// Здесь добавляй только специфичные для этого feature зависимости.
dependencies {
    // пример: если home нужен доступ к БД напрямую
    implementation(project(":core:domain"))
}
