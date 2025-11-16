plugins {
    groovy
    `java-library`
}

dependencies {
    api("org.hibernate.validator:hibernate-validator:9.0.1.Final")

    api("jakarta.ws.rs:jakarta.ws.rs-api:3.1.0")
    implementation(libs.jackson.annotations)
    api(libs.jackson.jakarta.rs.json.provider)
    api(libs.jackson.datatype.jsr310)
    implementation(libs.guava)
    api("com.damnhandy:handy-uri-templates:2.1.8")
    api("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")

    implementation("com.sun.xml.bind:jaxb-core:4.0.5")
    implementation("com.sun.xml.bind:jaxb-impl:4.0.5")
    implementation("jakarta.annotation:jakarta.annotation-api:3.0.0")

    testImplementation(libs.spock.core)
    testImplementation(libs.spock.junit4)
    testImplementation(project(":hermes-test-helper"))
    testRuntimeOnly(libs.junit.vintage.engine)
}
