val versions: Map<*, *> by rootProject.extra

dependencies {
    implementation(project(":hermes-api"))

    implementation("org.apache.avro:avro:${versions["avro"]}")
    implementation("com.google.guava:guava:${versions["guava"]}")

    testImplementation(project(":hermes-test-helper"))

    testImplementation("org.spockframework:spock-core:${versions["spock"]}")
    testImplementation("org.spockframework:spock-junit4:${versions["spock"]}")
}
