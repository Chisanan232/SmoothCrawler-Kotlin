/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin library project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.5/userguide/building_java_projects.html
 * This project uses @Incubating APIs which are subject to change.
 */

import kotlinx.kover.api.JacocoEngine
import kotlin.collections.listOf

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    kotlin("jvm") version "1.7.10"

    // Apply Kover to activate feature about testing coverage
    id("org.jetbrains.kotlinx.kover") version "0.6.0-Beta"
    // Apply Dokka to activate feature about documentation generation
    id("org.jetbrains.dokka") version "1.7.10"

    // Apply the java-library plugin for API and implementation separation.
    java
    `java-library`
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

sourceSets {
    main {
        java.srcDir(listOf(
            "smoothcrawler/main/kotlin",
        ))
    }

    test {
        java.srcDir(listOf(
            // Unit test
            "smoothcrawler/test/kotlin",
            // Integration test
            "smoothcrawler/test-integration/kotlin",
        ))
    }
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation("com.google.guava:guava:31.0.1-jre")

    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api("org.apache.commons:commons-math3:3.6.1")

    // The dependencies for testing
    testImplementation(kotlin("test"))
    // junit 5
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("io.mockk:mockk:1.12.7")
    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("org.json:json:20220320")
    // For testing code coverage of source code
    testImplementation("org.jetbrains.kotlinx:kover:0.6.0")

    // For Kotlin documentation generation
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.7.10")
}

val smoothcrawlerPackage = "org.smoothcrawler"

kover {
    isDisabled.set(false) // true to disable instrumentation and all Kover tasks in this project
//    engine.set(kotlinx.kover.api.DefaultIntellijEngine) // change Coverage Engine
    engine.set(JacocoEngine("0.8.8")) // change Coverage Engine
    filters { // common filters for all default Kover tasks
        classes { // common class filter for all default Kover tasks
            includes += smoothcrawlerPackage // class inclusion rules
//            excludes += listOf("com.example.subpackage.*") // class exclusion rules
        }
    }

    instrumentation {
        excludeTasks += "dummy-tests" // set of test tasks names to exclude from instrumentation. The results of their execution will not be presented in the report
    }

    xmlReport {
        onCheck.set(true) // true to run koverXmlReport task during the execution of the check task
        reportFile.set(layout.buildDirectory.file("$buildDir/reports/kover/xml/result.xml")) // change report file name
        overrideFilters {
            classes { // override common class filter
                includes += smoothcrawlerPackage // override class inclusion rules
//                excludes += listOf("com.example2.subpackage.*") // override class exclusion rules
            }
        }
    }

    htmlReport {
        onCheck.set(false) // true to run koverHtmlReport task during the execution of the check task
        reportDir.set(layout.buildDirectory.dir("$buildDir/reports/kover/html/html-result")) // change report directory
        overrideFilters {
            classes { // override common class filter
                includes += smoothcrawlerPackage // class inclusion rules
//                excludes += listOf("com.example2.subpackage.*") // override class exclusion rules
            }
        }
    }

    verify {
        onCheck.set(true) // true to run koverVerify task during the execution of the check task
        rule { // add verification rule
            isEnabled = true // false to disable rule checking
            name = null // custom name for the rule
            target = kotlinx.kover.api.VerificationTarget.ALL // specify by which entity the code for separate coverage evaluation will be grouped

            overrideClassFilter { // override common class filter
                includes += smoothcrawlerPackage // override class inclusion rules
//                excludes += listOf("com.example.verify.subpackage.*") // override class exclusion rules
            }

            bound { // add rule bound
                minValue = 75
                maxValue = 100
                counter = kotlinx.kover.api.CounterType.LINE // change coverage metric to evaluate (LINE, INSTRUCTION, BRANCH)
                valueType = kotlinx.kover.api.VerificationValueType.COVERED_PERCENTAGE // change counter value (COVERED_COUNT, MISSED_COUNT, COVERED_PERCENTAGE, MISSED_PERCENTAGE)
            }
        }
    }
}

tasks {
    test {
        useJUnitPlatform()

         // show standard out and standard error of the test JVM(s) on the console
        testLogging.showStandardStreams = true
    }
}
