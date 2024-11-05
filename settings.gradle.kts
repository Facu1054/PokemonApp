@file:Suppress("UnstableApiUsage")

include(":test:unit")


include(":framework:region")


include(":domain:region")


include(":feature:common")


include(":feature:detail")


include(":feature:home")


include(":feature")


include(":framework:core")



include(":framework:pokemon")



include(":framework")





pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "My Pokemon App"
include(":app")

include(":domain:pokemon")
