plugins {
    id(Plugins.application)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinAndroidExtensions)
    id(Plugins.navigationSafeArgsKotlin)
    id(Plugins.daggerHiltAndroidPlugin)
}

android {
    compileSdk = ConfigData.compileSdk

    defaultConfig {
        applicationId = ConfigData.applicationId
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = ConfigData.testInstrumentationRunner
    }

    buildTypes {
        release { // alternative is -> getByName("release") {
            isMinifyEnabled = ConfigData.debugMinifyEnabled
            proguardFiles(
                getDefaultProguardFile(ConfigData.defaultProguardFile),
                ConfigData.proguardRules
            )
        }
    }

    buildFeatures {
        dataBinding = ConfigData.dataBinding
        viewBinding = ConfigData.viewBinding
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = ConfigData.jvmTarget
    }
}

dependencies {
    // App Libraries
    implementation(AppDependencies.commonImplementationLibraries)
    implementation("androidx.databinding:databinding-runtime:7.3.1")
    kapt(AppDependencies.commonKaptLibraries)
    annotationProcessor(AppDependencies.commonAnnotationProcessorLibraries)


    // Test Libraries
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)
}