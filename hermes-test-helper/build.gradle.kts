plugins {
    `java-library`
}

dependencies {
    implementation(project(":hermes-api"))
    implementation(project(":hermes-common"))
    implementation(project(":hermes-consumers"))

    implementation(libs.jersey.client)
    implementation(libs.jersey.hk2)
    implementation(libs.jersey.proxy.client)
    api("commons-io:commons-io:2.16.1")
    api(libs.wiremock.standalone)
    api(libs.curator.test) {
        exclude(module = "slf4j-log4j12")
        exclude(module = "log4j")
    }
    implementation(libs.curator.client) {
        exclude(module = "slf4j-log4j12")
        exclude(module = "log4j")
    }
    implementation(libs.curator.recipes) {
        exclude(module = "slf4j-log4j12")
        exclude(module = "log4j")
    }
    implementation("com.github.spotbugs:spotbugs-annotations:4.8.4")
    implementation("org.awaitility:awaitility-groovy:4.2.1")
    implementation(libs.assertj.core)
    api("net.javacrumbs.json-unit:json-unit-fluent:3.2.7")
    implementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.3.1")
    implementation("com.jayway.jsonpath:json-path:2.9.0")
    implementation(libs.jackson.datatype.jsr310)
    implementation(libs.spring.test)
    implementation(libs.spring.webflux)
    implementation("org.awaitility:awaitility:4.2.0")
    testImplementation(libs.spock.core)
    testImplementation(libs.spock.junit4)

    implementation(libs.testcontainers)
    implementation(libs.testcontainers.toxiproxy)
    implementation(libs.testcontainers.gcloud)
}
