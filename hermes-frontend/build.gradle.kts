plugins {
    application
    `java-library`
}

application {
    mainClass = "pl.allegro.tech.hermes.frontend.HermesFrontend"
}

dependencies {
    implementation(project(":hermes-common"))
    api(project(":hermes-tracker"))
    implementation(project(":hermes-metrics"))
    implementation(project(":hermes-schema"))

    api(libs.spring.boot.starter)
    api(libs.undertow.core)
    // Did not update that as we're trying to abandon buffers
    api("net.openhft:chronicle-map:3.25ea6") {
        exclude(group = "net.openhft", module = "chronicle-analytics")
    }
    implementation("commons-io:commons-io:2.16.1")
    implementation(libs.failsafe)

    testImplementation(project(":hermes-test-helper"))

    testImplementation(libs.spock.core)
    testImplementation(libs.groovy.json)
    testImplementation("org.awaitility:awaitility-groovy:4.2.1")
    testImplementation("org.awaitility:awaitility:4.2.1")
    testImplementation(libs.testcontainers.spock)
    testImplementation(libs.testcontainers.kafka)
    testRuntimeOnly(libs.junit.vintage.engine)
}
