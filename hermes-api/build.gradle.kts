val versions: Map<*, *> by rootProject.extra

plugins {
    groovy
    `java-library`
}

dependencies {
    api("org.hibernate.validator:hibernate-validator:9.0.1.Final")

    api("jakarta.ws.rs:jakarta.ws.rs-api:3.1.0")
    implementation("com.fasterxml.jackson.core:jackson-annotations:${versions["jackson"]}")
    api("com.fasterxml.jackson.jakarta.rs:jackson-jakarta-rs-json-provider:${versions["jackson"]}")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${versions["jackson"]}")
    implementation("com.google.guava:guava:${versions["guava"]}")
    api("com.damnhandy:handy-uri-templates:2.1.8")
    api("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")

    implementation("com.sun.xml.bind:jaxb-core:4.0.5")
    implementation("com.sun.xml.bind:jaxb-impl:4.0.5")
    implementation("jakarta.annotation:jakarta.annotation-api:3.0.0")

    testImplementation("org.spockframework:spock-core:${versions["spock"]}")
    testImplementation("org.spockframework:spock-junit4:${versions["spock"]}")
    testImplementation(project(":hermes-test-helper"))
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:${versions["junit_jupiter"]}")
}
