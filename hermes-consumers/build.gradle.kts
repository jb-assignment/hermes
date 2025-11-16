plugins {
    application
    `java-library`
}

application {
    mainClass = "pl.allegro.tech.hermes.consumers.HermesConsumers"
}

val sbeClasspath: Configuration by configurations.creating

dependencies {
    implementation(project(":hermes-common"))
    api(project(":hermes-tracker"))
    implementation(project(":hermes-metrics"))
    implementation(project(":hermes-schema"))

    api(libs.spring.boot.starter)
    api(libs.jetty.alpn.java.client)
    api(libs.jetty.http2.client.transport)
    implementation("org.jctools:jctools-core:4.0.3")
    api("jakarta.jms:jakarta.jms-api:3.1.0")
    implementation("com.github.rholder:guava-retrying:2.0.0") {
        exclude(module = "guava")
    }
    implementation("org.agrona:agrona:1.21.1")
    // TODO: can we update it ? Which version of server our clients use ?
    implementation("org.hornetq:hornetq-jms-client:2.4.1.Final") {
        exclude(module = "hornetq-native")
    }
    api("com.google.cloud:google-cloud-pubsub:1.128.1")
    api("org.apache.httpcomponents.core5:httpcore5:5.2.4")
    implementation(libs.json2avro.converter)

    testImplementation(project(":hermes-test-helper"))
    testImplementation(libs.curator.test)
    testImplementation("jakarta.servlet:jakarta.servlet-api:6.0.0")

    testImplementation(project(":hermes-common"))

    testImplementation("org.awaitility:awaitility-groovy:4.2.1")
    testImplementation(libs.spock.core)
    testImplementation(libs.spock.junit4)
    testRuntimeOnly(libs.junit.vintage.engine)

    sbeClasspath("uk.co.real-logic:sbe-all:1.31.1")
}

val generatedPath = "${layout.buildDirectory.get()}/generated/java/"

val generateMaxRateSbeStubs by tasks.registering(JavaExec::class) {
    description = "Generate SBE stubs for max-rate"
    classpath = sbeClasspath
    mainClass = "uk.co.real_logic.sbe.SbeTool"
    systemProperties(
        "sbe.output.dir" to generatedPath,
        "sbe.xinclude.aware" to "true"
    )
    args = listOf("src/main/resources/sbe/max-rate.xml")
}

val generateWorkloadSbeStubs by tasks.registering(JavaExec::class) {
    description = "Generate SBE stubs for workload"
    classpath = sbeClasspath
    mainClass = "uk.co.real_logic.sbe.SbeTool"
    systemProperties(
        "sbe.output.dir" to generatedPath,
        "sbe.xinclude.aware" to "true"
    )
    args = listOf("src/main/resources/sbe/workload.xml")
}

val generateSbeStubs by tasks.registering {
    description = "Generate all SBE stubs from provided schemas"
    dependsOn(generateMaxRateSbeStubs, generateWorkloadSbeStubs)
}

sourceSets {
    main {
        java.srcDir(generatedPath)
    }
}

tasks.compileJava {
    dependsOn(generateSbeStubs)
}
