val chronicleMapJvmArgs: List<*> by rootProject.extra

plugins {
    alias(libs.plugins.jmh)
}

val jmh: Configuration by configurations.getting

jmh {
    includes.set(listOf("pl\\.allegro\\.tech\\.hermes\\.benchmark\\..*"))
    jmhVersion.set("1.36")
    zip64.set(true)
    verbosity.set("NORMAL")
    iterations.set(intProperty("jmh.iterations", 4))
    timeOnIteration.set(stringProperty("jmh.timeOnIteration", "80s"))
    fork.set(intProperty("jmh.fork", 1))
    warmupIterations.set(intProperty("jmh.warmupIterations", 4))
    warmup.set(stringProperty("jmh.timeOnWarmupIteration", "80s"))
    @Suppress("UNCHECKED_CAST")
    jvmArgs.set(listProperty("jmh.jvmArgs", listOf("-Xmx1g", "-Xms1g", "-XX:+UseG1GC") + (chronicleMapJvmArgs as List<String>)))
    failOnError.set(booleanProperty("jmh.failOnError", true))
    threads.set(intProperty("jmh.threads", 4))
    synchronizeIterations.set(false)
    forceGC.set(false)
    duplicateClassesStrategy.set(DuplicatesStrategy.EXCLUDE)
}

dependencies {
    jmh("org.openjdk.jmh:jmh-core:1.37")
    jmh("org.openjdk.jmh:jmh-generator-annprocess:1.37")
    jmh("org.apache.httpcomponents:httpasyncclient:4.1.5")
    jmh(projects.hermesFrontend)
    jmh(projects.hermesTestHelper)
    jmh(projects.hermesCommon)
    jmh(projects.hermesTracker)
}

// Workaround for duplicated `BenchmarkList` and `CompilerHints` files from META-INF directory in jmh jar.
// Those duplications can prevent from running benchmark tests.
// More info https://github.com/melix/jmh-gradle-plugin/issues/6
tasks.named<Jar>("jmhJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.named<ProcessResources>("processJmhResources") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

fun stringProperty(property: String, defaultValue: String): String {
    return if (project.hasProperty(property)) project.property(property) as String else defaultValue
}

fun listProperty(property: String, defaultValue: List<String>): List<String> {
    return if (project.hasProperty(property)) (project.property(property) as String).split(" ") else defaultValue
}

fun intProperty(property: String, defaultValue: Int): Int {
    return if (project.hasProperty(property)) (project.property(property) as String).toInt() else defaultValue
}

fun booleanProperty(property: String, defaultValue: Boolean): Boolean {
    return if (project.hasProperty(property)) (project.property(property) as String).toBoolean() else defaultValue
}
