plugins {
    id("com.android.application") version "8.0.2" apply false
    id("com.android.library") version "8.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.3.15") // Google Services Plugin
        classpath ("com.android.tools.build:gradle:8.1.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
