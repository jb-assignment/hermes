val versions: Map<*, *> by rootProject.extra

plugins {
    groovy
    java
    `java-library`
}

dependencies {
    implementation("junit:junit:4.13.2")
    api("org.wiremock:wiremock-standalone:${versions["wiremock"]}")
    implementation("org.awaitility:awaitility:4.2.1")
    api("org.apache.avro:avro:${versions["avro"]}")
    implementation("tech.allegro.schema.json2avro:converter:${versions["json2avro"]}")
    implementation("org.junit.jupiter:junit-jupiter-api:${versions["junit_jupiter"]}")

    testImplementation(project(":hermes-test-helper"))
    testImplementation("org.spockframework:spock-core:${versions["spock"]}")
    testImplementation("org.spockframework:spock-junit4:${versions["spock"]}")
    testImplementation("org.apache.groovy:groovy-json:${versions["groovy"]}")
    testImplementation("org.glassfish.jersey.core:jersey-client:${versions["jersey"]}")
    testImplementation("org.glassfish.jersey.inject:jersey-hk2:${versions["jersey"]}")
    testImplementation("org.junit.jupiter:junit-jupiter:${versions["junit_jupiter"]}")
    testImplementation("org.springframework:spring-test:${versions["spring_web"]}")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:${versions["junit_jupiter"]}")
}

tasks.test {
    useJUnitPlatform()
}
