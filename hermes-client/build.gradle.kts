val versions: Map<*, *> by rootProject.extra

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
    compileOnly("io.micrometer:micrometer-core:${versions["micrometer_metrics"]}")
    compileOnly("org.glassfish.jersey.core:jersey-client:${versions["jersey"]}")
    compileOnly("org.glassfish.jersey.inject:jersey-hk2:${versions["jersey"]}")
    compileOnly("org.springframework:spring-web:${versions["spring_web"]}")
    compileOnly("org.springframework:spring-webflux:${versions["spring_web"]}")
    compileOnly("com.squareup.okhttp3:okhttp:${versions["okhttp"]}")

    implementation("dev.failsafe:failsafe:${versions["failsafe"]}")
    api("io.projectreactor:reactor-core:3.6.5")

    testImplementation("org.spockframework:spock-core:${versions["spock"]}")
    testImplementation("org.spockframework:spock-junit4:${versions["spock"]}")
    testImplementation("org.wiremock:wiremock-standalone:${versions["wiremock"]}")
    testImplementation("jakarta.servlet:jakarta.servlet-api:6.0.0")
    testImplementation("com.jayway.jsonpath:json-path:2.9.0")

    testImplementation("io.micrometer:micrometer-core:${versions["micrometer_metrics"]}")
    testImplementation("org.glassfish.jersey.core:jersey-client:${versions["jersey"]}")
    testImplementation("org.glassfish.jersey.inject:jersey-hk2:${versions["jersey"]}")
    testImplementation("org.springframework:spring-web:${versions["spring_web"]}")
    testImplementation("org.springframework:spring-context:${versions["spring_web"]}")
    testImplementation("org.springframework:spring-webflux:${versions["spring_web"]}")
    testImplementation("com.squareup.okhttp3:okhttp:${versions["okhttp"]}")
    testImplementation("io.projectreactor.netty:reactor-netty:1.1.18")
    testImplementation("io.projectreactor:reactor-test:3.6.5")
}
