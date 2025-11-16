package conventions

import chronicleJvmArgs

plugins {
    java
    jacoco
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
    withJavadocJar()
    withSourcesJar()
}

tasks {
    test {
        useJUnitPlatform()
        val args = mutableListOf<String>()
        if (project.hasProperty("tests.timeout.multiplier")) {
            args.add("-Dtests.timeout.multiplier=${project.property("tests.timeout.multiplier")}")
        }
        args.addAll(chronicleJvmArgs)
        jvmArgs = args
    }

    javadoc {
        (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
    }
}