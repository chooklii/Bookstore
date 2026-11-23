import org.gradle.internal.extensions.core.extra

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

// todo remove hardcoded 21 and infer it
val sourceCompatibility by extra(21)
