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
    testImplementation(libs.spock.core)
    testImplementation(libs.spock.junit4)
    testImplementation(libs.testcontainers.elasticsearch)
    testRuntimeOnly(libs.junit.vintage.engine)
}
