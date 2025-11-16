import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.buildSteps.nodeJS
import jetbrains.buildServer.configs.kotlin.triggers.vcs

version = "2025.07"

project {
    sequential {
        parallel {
            buildType(Gradle("Unit tests", "check"))
            buildType(Gradle("Integration tests", "integrationTest"))
            buildType(Gradle("Slow integration tests", "slowIntegrationTest"))
            buildType(Gradle("JMH benchmark", "jmh"))
        }
    }
}

class Gradle(buildTypeName: String, tasks: String) : BuildType() {
    init {
        name = buildTypeName
        id(buildTypeName.toId())

        vcs {
            root(DslContext.settingsRoot)
        }

        steps {
            nodeJS {
                name = "Install Node"
                workingDir = "hermes-console"
                shellScript = "npm install"
            }

            gradle {
                name = "Run tests"
                this.tasks = tasks
            }
        }

        triggers {
            vcs {  }
        }
    }
}
