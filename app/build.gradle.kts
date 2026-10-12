plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.localai"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.localai"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    // הגדרה למניעת דחיסה של קובץ המודל (כדי שניתן ייהיה לקרוא אותו במהירות)
    aaptOptions {
        noCompress("bin", "gguf", "task")
    }
}

dependencies {
    // ספריית MediaPipe להרצת מודלי שפה מקומית
    implementation("com.google.mediapipe:tasks-genai:0.10.14")
    
    // ספריות בסיס לאנדרואיד
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
}
