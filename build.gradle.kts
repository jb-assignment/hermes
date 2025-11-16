import java.time.Duration
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.publish.maven.MavenPublication
import pl.allegro.tech.build.axion.release.domain.PredefinedVersionCreator
import pl.allegro.tech.build.axion.release.domain.PredefinedVersionCreator.VERSION_WITH_BRANCH

plugins {
    `maven-publish`
    alias(libs.plugins.axion.release)
    alias(libs.plugins.nexus.publish)
    id("conventions.java")
    id("conventions.buildscript-helpers")
}

scmVersion {
    tag {
        prefix = "hermes-"
    }
    versionCreator = VERSION_WITH_BRANCH.versionCreator
}

nexusPublishing {
    connectTimeout = Duration.ofMinutes(intProperty("publishingTimeoutInMin", 10).toLong())
    clientTimeout = Duration.ofMinutes(intProperty("publishingTimeoutInMin", 10).toLong())
    repositories {
        sonatype {
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
            stagingProfileId = "19d6feb4b1fb3" // id for group 'pl.allegro.tech.hermes'
            username = System.getenv("SONATYPE_USERNAME")
            password = System.getenv("SONATYPE_PASSWORD")
        }
    }
    transitionCheckOptions {
        maxRetries.set(intProperty("attemptsToCloseStagingRepository", 30))
        delayBetween.set(Duration.ofSeconds(intProperty("delayInSecBetweenCloseStagingRepositoryAttempts", 45).toLong()))
    }
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "groovy")

    group = "pl.allegro.tech.hermes"
    version = rootProject.scmVersion.version

    dependencies {
        val libs = rootProject.libs

        implementation(libs.slf4j.api)
        implementation(libs.commons.lang3)

        // Allure Spock adapter
        testImplementation(platform(libs.allure.bom))
        testImplementation(libs.allure.spock2)
        testImplementation(libs.allure.junit.platform)

        // Spock framework
        testImplementation(platform(libs.spock.bom))
        testImplementation(libs.spock.core)

        testImplementation(libs.junit)
        testImplementation(libs.junit.dataprovider)
        testImplementation(libs.mockito.core)
        testImplementation(libs.assertj.core)
        testImplementation(libs.awaitility)

        annotationProcessor(libs.spring.boot.configuration.processor)
    }
}

subprojects {
    val libs = rootProject.libs

    configurations.all {
        exclude(group = "org.slf4j", module = "slf4j-log4j12")
        exclude(group = "log4j", module = "log4j")
        resolutionStrategy {
            force(libs.guava.core.get().toString())
            force(libs.jackson.databind.get().toString())
            force(libs.jackson.annotations.get().toString())
            force(libs.jackson.jaxrs.json.provider.get().toString())
        }
    }

    tasks.withType<JavaCompile> {
        options.compilerArgs.addAll(listOf("-Xlint:unchecked,deprecation"))
    }

    tasks.test {
        reports {
            html.required = false
            junitXml.required = true
            junitXml.outputLocation = file("${layout.buildDirectory.get()}/test-results/$name")
        }

        testLogging {
            exceptionFormat = TestExceptionFormat.FULL
            events("passed", "skipped", "failed")
        }
    }
}

dependencyAnalysis {
    issues {
        all {
            onAny {
                severity("warn")
            }
        }
    }
}
