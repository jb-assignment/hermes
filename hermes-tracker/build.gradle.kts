val versions: Map<*, *> by rootProject.extra

val testArtifacts: Configuration by configurations.creating

dependencies {
    implementation(project(":hermes-api"))
    implementation(project(":hermes-metrics"))
    testImplementation(project(":hermes-test-helper"))
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:${versions["junit_jupiter"]}")
}

val testJar by tasks.registering(Jar::class) {
    archiveClassifier = "tests"
    from(sourceSets["test"].output)
}

artifacts {
    add(testArtifacts.name, testJar)
}