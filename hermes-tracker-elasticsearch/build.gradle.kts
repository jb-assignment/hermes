val versions: Map<*, *> by rootProject.extra

plugins {
    `java-library`
}

dependencies {
    implementation(project(":hermes-common"))
    implementation(project(":hermes-tracker"))
    implementation("org.slf4j:slf4j-api:2.0.13")
    api("org.elasticsearch.client:transport:7.10.2")

    testImplementation(project(":hermes-tracker", configuration = "testArtifacts"))
    testImplementation(project(":hermes-test-helper"))
    testImplementation("org.spockframework:spock-core:${versions["spock"]}")
    testImplementation("org.spockframework:spock-junit4:${versions["spock"]}")
    testImplementation("org.testcontainers:elasticsearch:1.20.3")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:${versions["junit_jupiter"]}")
}