plugins {
    `java-library`
    application
    groovy
    alias(libs.plugins.node.gradle)
    id("conventions.java")
}

application {
    mainClass = "pl.allegro.tech.hermes.management.HermesManagement"
}

dependencies {
    api(projects.hermesApi)
    api(projects.hermesCommon)
    api(projects.hermesTracker)
    implementation(projects.hermesSchema)

    api(libs.spring.boot.starter.web)
    api(libs.spring.boot.starter.actuator)
    api(libs.spring.boot.starter.jersey)
    implementation(libs.jopt.simple)
    implementation(libs.jersey.mvc.freemarker)

    implementation(libs.swagger.jersey2.jaxrs) {
        exclude(group = "javax.validation", module = "validation-api")
    }

    implementation(libs.kafka.clients)

    implementation(libs.commons.codec)
    implementation(libs.json.schema.validator)

    implementation(libs.commons.jxpath)
    implementation(libs.httpclient5)

    api(libs.javers.core)

    implementation(libs.jackson.datatype.jsr310)
    implementation(libs.commons.io)

    testImplementation(projects.hermesTestHelper)
    testImplementation(libs.spring.boot.starter.test)

    testImplementation(libs.spock.core)
    testImplementation(libs.spock.junit4)
    testImplementation(libs.spock.spring)
    testImplementation(libs.groovy.json)

    testImplementation(libs.testcontainers.spock)
    testImplementation(libs.testcontainers.kafka)
}

node {
    version = "20.4.0"
    distBaseUrl = null
    download = true
    workDir = file("${layout.buildDirectory.get()}/nodejs")
    npmWorkDir = file("${layout.buildDirectory.get()}/npm")
    nodeProjectDir = file("${project.rootDir}/hermes-console")
}

