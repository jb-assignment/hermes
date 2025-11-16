val versions: Map<*, *> by rootProject.extra

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

    api("org.springframework.boot:spring-boot-starter:${versions["spring"]}")
    api("io.undertow:undertow-core:${versions["undertow"]}")
    // Did not update that as we're trying to abandon buffers
    api("net.openhft:chronicle-map:3.25ea6") {
        exclude(group = "net.openhft", module = "chronicle-analytics")
    }
    implementation("commons-io:commons-io:2.16.1")
    implementation("dev.failsafe:failsafe:${versions["failsafe"]}")

    testImplementation(project(":hermes-test-helper"))

    testImplementation("org.spockframework:spock-core:${versions["spock"]}")
    testImplementation("org.apache.groovy:groovy-json:${versions["groovy"]}")
    testImplementation("org.awaitility:awaitility-groovy:4.2.1")
    testImplementation("org.awaitility:awaitility:4.2.1")
    testImplementation("org.testcontainers:spock:${versions["testcontainers"]}")
    testImplementation("org.testcontainers:kafka:${versions["testcontainers"]}")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:${versions["junit_jupiter"]}")
}
