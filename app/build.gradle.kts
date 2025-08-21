plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // 1) Safe Args plugin (opcional, solo si quieres generar clases tipo-safe)
    // añade esta línea si quieres usar safe args:
    // alias(libs.plugins.navigation.safeargs)
}

android {
    namespace = "com.example.icfes_up"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.icfes_up"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Core
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    // Lifecycle
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)

    // Navigation Component
    implementation(libs.navigation.fragment)      // androidx.navigation:navigation-fragment:2.x.x
    implementation(libs.navigation.ui)            // androidx.navigation:navigation-ui:2.x.x

    // KTX extensions (findNavController(), navigateSafe(), etc.)
    implementation(libs.navigation.fragment.ktx)  // androidx.navigation:navigation-fragment-ktx:2.x.x
    implementation(libs.navigation.ui.ktx)        // androidx.navigation:navigation-ui-ktx:2.x.x

    // Activity KTX
    implementation(libs.activity)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
