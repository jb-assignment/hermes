val versions: Map<*, *> by rootProject.extra

plugins {
    `java-library`
}

dependencies {
    implementation(project(":hermes-api"))
    implementation(project(":hermes-common"))
    implementation(project(":hermes-consumers"))

    implementation("org.glassfish.jersey.core:jersey-client:${versions["jersey"]}")
    implementation("org.glassfish.jersey.inject:jersey-hk2:${versions["jersey"]}")
    implementation("org.glassfish.jersey.ext:jersey-proxy-client:${versions["jersey"]}")
    api("commons-io:commons-io:2.16.1")
    api("org.wiremock:wiremock-standalone:${versions["wiremock"]}")
    api("org.apache.curator:curator-test:${versions["curator"]}") {
        exclude(module = "slf4j-log4j12")
        exclude(module = "log4j")
    }
    implementation("org.apache.curator:curator-client:${versions["curator"]}") {
        exclude(module = "slf4j-log4j12")
        exclude(module = "log4j")
    }
    implementation("org.apache.curator:curator-recipes:${versions["curator"]}") {
        exclude(module = "slf4j-log4j12")
        exclude(module = "log4j")
    }
    implementation("com.github.spotbugs:spotbugs-annotations:4.8.4")
    implementation("org.awaitility:awaitility-groovy:4.2.1")
    implementation("org.assertj:assertj-core:${versions["assertj"]}")
    api("net.javacrumbs.json-unit:json-unit-fluent:3.2.7")
    implementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.3.1")
    implementation("com.jayway.jsonpath:json-path:2.9.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${versions["jackson"]}")
    implementation("org.springframework:spring-test:${versions["spring_web"]}")
    implementation("org.springframework:spring-webflux:${versions["spring_web"]}")
    implementation("org.awaitility:awaitility:4.2.0")
    testImplementation("org.spockframework:spock-core:${versions["spock"]}")
    testImplementation("org.spockframework:spock-junit4:${versions["spock"]}")

    implementation("org.testcontainers:testcontainers:${versions["testcontainers"]}")
    implementation("org.testcontainers:toxiproxy:${versions["testcontainers"]}")
    implementation("org.testcontainers:gcloud:${versions["testcontainers"]}")
}
