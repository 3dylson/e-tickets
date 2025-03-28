plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version "1.9.0"
    id("com.android.library")
    id("org.jetbrains.compose")
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
            export("dev.icerock.moko:resources:0.23.0")
            export("dev.icerock.moko:graphics:0.9.0") // toUIColor here
        }
        extraSpecAttributes["resources"] =
            "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.materialIconsExtended)
                api(compose.ui)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                api(compose.foundation)
                api(compose.animation)

                implementation("media.kamel:kamel-image:0.7.1")

                implementation("io.ktor:ktor-client-core:2.3.2")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.1")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.1")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

                api("dev.icerock.moko:mvvm-core:0.16.1") // only ViewModel, EventsDispatcher, Dispatchers.UI
                api("dev.icerock.moko:mvvm-compose:0.16.1") // api mvvm-core, getViewModel for Compose Multiplatform
                api("dev.icerock.moko:resources:0.23.0") // StringResource, StringPluralsResource, StringArrayResource
                api("dev.icerock.moko:resources-compose:0.23.0") // for compose multiplatform

                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

                api("moe.tlaster:precompose:1.5.0-beta01")
                // api("moe.tlaster:precompose-molecule:$precompose_version") // For Molecule intergration
                // api("moe.tlaster:precompose-viewmodel:$precompose_version") // For ViewModel intergration

                implementation("org.kodein.di:kodein-di:7.12.0")


            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.7.2")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")

                implementation("io.ktor:ktor-client-android:2.3.2")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        //TODO delete after fix https://github.com/icerockdev/moko-resources/issues/510#issuecomment-1619141070
        iosX64Main.resources.srcDirs("build/generated/moko/iosX64Main/src")
        iosArm64Main.resources.srcDirs("build/generated/moko/iosArm64Main/src")
        iosSimulatorArm64Main.resources.srcDirs("build/generated/moko/iosSimulatorArm64Main/src")

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.3.2")
            }
        }

    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "pt.etickets.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    //TODO delete after fix https://github.com/icerockdev/moko-resources/issues/510#issuecomment-1619141070
    sourceSets["main"].java.srcDirs("build/generated/moko/androidMain/src")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "pt.etickets"
    multiplatformResourcesClassName = "SharedRes"
}
