plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.grammarKitComposer)
}

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    maven("https://cache-redirector.jetbrains.com/download-pgp-verifier")
    // Grazie
    maven("https://cache-redirector.jetbrains.com/packages.jetbrains.team/maven/p/grazi/grazie-platform-public")
}

grammarKit {
    intellijRelease.set(libs.versions.intellij)
}

dependencies {
    implementation(libs.sqldelight.dialect.api)
    implementation(libs.sqldelight.postgresql.dialect)
    implementation(libs.sqldelight.compiler.env)
}

