val versions: Map<*, *> by rootProject.extra

plugins {
    `java-library`
}

dependencies {
    api("io.dropwizard.metrics:metrics-core:${versions["dropwizard_metrics"]}")
    api("org.apache.commons:commons-text:1.12.0")
    api("io.micrometer:micrometer-core:${versions["micrometer_metrics"]}")

    testImplementation("org.spockframework:spock-core:${versions["spock"]}")
    testImplementation("org.spockframework:spock-junit4:${versions["spock"]}")
}