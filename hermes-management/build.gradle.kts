import com.github.gradle.node.yarn.task.YarnTask

val versions: Map<*, *> by rootProject.extra

plugins {
    `java-library`
    application
    id("com.github.node-gradle.node") version "7.0.2"
}

application {
    mainClass = "pl.allegro.tech.hermes.management.HermesManagement"
}

dependencies {
    api(project(":hermes-api"))
    api(project(":hermes-common"))
    api(project(":hermes-tracker"))
    implementation(project(":hermes-schema"))

    api("org.springframework.boot:spring-boot-starter-web:${versions["spring"]}")
    api("org.springframework.boot:spring-boot-starter-actuator:${versions["spring"]}")
    api("org.springframework.boot:spring-boot-starter-jersey:${versions["spring"]}")
    implementation("net.sf.jopt-simple:jopt-simple:5.0.4")
    implementation("org.glassfish.jersey.ext:jersey-mvc-freemarker:${versions["jersey"]}")

    implementation("io.swagger:swagger-jersey2-jaxrs:1.6.14") {
        exclude(group = "javax.validation", module = "validation-api")
    }

    implementation("org.apache.kafka:kafka-clients:${versions["kafka"]}")

    implementation("commons-codec:commons-codec:1.16.1")
    implementation("com.github.java-json-tools:json-schema-validator:2.2.14")

    implementation("commons-jxpath:commons-jxpath:1.3")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.3.1")

    api("org.javers:javers-core:7.4.2")

    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${versions["jackson"]}")
    implementation("commons-io:commons-io:2.16.1")

    testImplementation(project(":hermes-test-helper"))
    testImplementation("org.springframework.boot:spring-boot-starter-test:${versions["spring"]}")

    testImplementation("org.spockframework:spock-core:${versions["spock"]}")
    testImplementation("org.spockframework:spock-junit4:${versions["spock"]}")
    testImplementation("org.spockframework:spock-spring:${versions["spock"]}")
    testImplementation("org.apache.groovy:groovy-json:${versions["groovy"]}")

    testImplementation("org.testcontainers:spock:${versions["testcontainers"]}")
    testImplementation("org.testcontainers:kafka:${versions["testcontainers"]}")
}

node {
    version = "20.4.0"
    distBaseUrl = "https://nodejs.org/dist"
    download = true
    workDir = file("${layout.buildDirectory.get()}/nodejs")
    npmWorkDir = file("${layout.buildDirectory.get()}/npm")
    nodeProjectDir = file("${project.rootDir}/hermes-console")
}

tasks.named("yarnSetup") {
    dependsOn(tasks.named("nodeSetup"))
}

tasks.named("yarn") {
    dependsOn(tasks.named("npmSetup"))
}

val buildHermesConsole by tasks.registering(YarnTask::class) {
    val tasksThatDontRequireConsole = listOf(
        "integrationTest",
        "slowIntegrationTest",
        "check"
    )

    onlyIf {
        tasksThatDontRequireConsole.intersect(gradle.startParameter.taskNames.toSet()).isEmpty()
    }

    args = listOf("build-only")
}

tasks.named("yarn") {
    finalizedBy(buildHermesConsole)
}

val attachHermesConsole by tasks.registering(Copy::class) {
    dependsOn(buildHermesConsole)
    from("../hermes-console/dist")
    val staticDirectory = "${sourceSets.main.get().output.resourcesDir!!.path}/static"
    // remove previous static dir if exists and start with clear setup
    doFirst {
        delete(staticDirectory)
    }
    into(staticDirectory)
}

val prepareIndexTemplate by tasks.registering {
    doLast {
        val indexPath = "${sourceSets.main.get().output.resourcesDir!!.path}/static/index.html"
        ant.withGroovyBuilder {
            "move"("file" to indexPath, "tofile" to "$indexPath.ftl")
        }
    }
}

tasks.named("compileTestGroovy") {
    dependsOn(attachHermesConsole)
}

tasks.named("javadoc") {
    dependsOn(attachHermesConsole)
}

tasks.jar {
    dependsOn(attachHermesConsole, prepareIndexTemplate)
}

tasks.named("run") {
    dependsOn(attachHermesConsole, prepareIndexTemplate)
}

