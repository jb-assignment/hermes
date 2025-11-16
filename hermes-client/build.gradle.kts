plugins {
    `java-library`
}

java {
    // We need to support Java 17 until all of our clients migrate to Java 21.
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

dependencies {
    compileOnly(libs.micrometer.core)
    compileOnly(libs.jersey.client)
    compileOnly(libs.jersey.hk2)
    compileOnly(libs.spring.web)
    compileOnly(libs.spring.webflux)
    compileOnly(libs.okhttp)

    implementation(libs.failsafe)
    api("io.projectreactor:reactor-core:3.6.5")

    testImplementation(libs.spock.core)
    testImplementation(libs.spock.junit4)
    testImplementation(libs.wiremock.standalone)
    testImplementation("jakarta.servlet:jakarta.servlet-api:6.0.0")
    testImplementation("com.jayway.jsonpath:json-path:2.9.0")

    testImplementation(libs.micrometer.core)
    testImplementation(libs.jersey.client)
    testImplementation(libs.jersey.hk2)
    testImplementation(libs.spring.web)
    testImplementation(libs.spring.context)
    testImplementation(libs.spring.webflux)
    testImplementation(libs.okhttp)
    testImplementation("io.projectreactor.netty:reactor-netty:1.1.18")
    testImplementation("io.projectreactor:reactor-test:3.6.5")
}
