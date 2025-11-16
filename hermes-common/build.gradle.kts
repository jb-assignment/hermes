plugins {
    `java-library`
    groovy
}

dependencies {
    api(project(":hermes-api"))
    api(project(":hermes-metrics"))
    api(project(":hermes-schema"))

    api(libs.curator.client) {
        exclude(module = "slf4j-log4j12")
        exclude(module = "log4j")
    }
    api(libs.curator.recipes) {
        exclude(module = "slf4j-log4j12")
        exclude(module = "log4j")
    }

    api(libs.jersey.client)
    implementation(libs.jersey.hk2)
    api(libs.jersey.media.json.jackson)
    api(libs.jersey.bean.validation)

    api(libs.json2avro.converter)

    api("org.apache.commons:commons-collections4:4.4")
    implementation("commons-codec:commons-codec:1.16.1")
    implementation(libs.guava)

    api(libs.jackson.databind)
    api(libs.avro)
    api("com.jayway.jsonpath:json-path:2.9.0")

    implementation(libs.dropwizard.metrics.core)

    implementation("com.google.code.findbugs:annotations:3.0.1")
    api(libs.micrometer.core)
    api(libs.micrometer.registry.prometheus)

    implementation("org.slf4j:log4j-over-slf4j:2.0.13")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    api(libs.kafka.clients) {
        exclude(group = "net.sf.jopt-simple")
    }

    api("jakarta.inject:jakarta.inject-api:2.0.1")

    testImplementation(project(":hermes-test-helper"))

    testImplementation("jakarta.servlet:jakarta.servlet-api:6.0.0")

    testImplementation(libs.spock.core)
    testImplementation(libs.spock.junit4)
    testImplementation("org.awaitility:awaitility-groovy:4.2.1")
    testRuntimeOnly(libs.junit.vintage.engine)
}
