val versions: Map<*, *> by rootProject.extra

plugins {
    `java-library`
    groovy
}

dependencies {
    api(project(":hermes-api"))
    api(project(":hermes-metrics"))
    api(project(":hermes-schema"))

    api("org.apache.curator:curator-client:${versions["curator"]}") {
        exclude(module = "slf4j-log4j12")
        exclude(module = "log4j")
    }
    api("org.apache.curator:curator-recipes:${versions["curator"]}") {
        exclude(module = "slf4j-log4j12")
        exclude(module = "log4j")
    }

    api("org.glassfish.jersey.core:jersey-client:${versions["jersey"]}")
    implementation("org.glassfish.jersey.inject:jersey-hk2:${versions["jersey"]}")
    api("org.glassfish.jersey.media:jersey-media-json-jackson:${versions["jersey"]}")
    api("org.glassfish.jersey.ext:jersey-bean-validation:${versions["jersey"]}")

    api("tech.allegro.schema.json2avro:converter:${versions["json2avro"]}")

    api("org.apache.commons:commons-collections4:4.4")
    implementation("commons-codec:commons-codec:1.16.1")
    implementation("com.google.guava:guava:${versions["guava"]}")

    api("com.fasterxml.jackson.core:jackson-databind:${versions["jackson"]}")
    api("org.apache.avro:avro:${versions["avro"]}")
    api("com.jayway.jsonpath:json-path:2.9.0")

    implementation("io.dropwizard.metrics:metrics-core:${versions["dropwizard_metrics"]}")

    implementation("com.google.code.findbugs:annotations:3.0.1")
    api("io.micrometer:micrometer-core:${versions["micrometer_metrics"]}")
    api("io.micrometer:micrometer-registry-prometheus:${versions["micrometer_metrics"]}")

    implementation("org.slf4j:log4j-over-slf4j:2.0.13")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    api("org.apache.kafka:kafka-clients:${versions["kafka"]}") {
        exclude(group = "net.sf.jopt-simple")
    }

    api("jakarta.inject:jakarta.inject-api:2.0.1")

    testImplementation(project(":hermes-test-helper"))

    testImplementation("jakarta.servlet:jakarta.servlet-api:6.0.0")

    testImplementation("org.spockframework:spock-core:${versions["spock"]}")
    testImplementation("org.spockframework:spock-junit4:${versions["spock"]}")
    testImplementation("org.awaitility:awaitility-groovy:4.2.1")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:${versions["junit_jupiter"]}")
}
