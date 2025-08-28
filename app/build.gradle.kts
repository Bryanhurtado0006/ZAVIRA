plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
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

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.activity)

    implementation (libs.bcrypt)


    implementation (libs.converter.gson.v290)
    implementation (libs.logging.interceptor)
    implementation(libs.retrofit.v290)


        // Material Design
        implementation (libs.material.v190)

        // Lottie para animaciones
        implementation (libs.lottie)

        // CardView (ya lo usas, pero te pongo la última)
        implementation (libs.cardview)

        // ConstraintLayout (para pantallas más modernas)
        implementation (libs.constraintlayout.v214)


        implementation (libs.material)
        implementation (libs.lottie.v630)
        implementation (libs.viewpager2)
        implementation (libs.lottie.v640)
        implementation (libs.lottie.v640)





    implementation(libs.core.ktx)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}