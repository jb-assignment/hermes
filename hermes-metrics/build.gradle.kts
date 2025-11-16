plugins {
    `java-library`
}

dependencies {
    api(libs.dropwizard.metrics.core)
    api("org.apache.commons:commons-text:1.12.0")
    api(libs.micrometer.core)

    testImplementation(libs.spock.core)
    testImplementation(libs.spock.junit4)
}
