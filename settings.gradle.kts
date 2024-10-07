pluginManagement {
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

rootProject.name = "CourseAndroidAppsKotlin"
include(":app")
include(":buildyourfirtsapps_1")
include(":aboutme_2")
include(":colormyviews_2")
include(":projecttourandroidtrivia_3")
include(":project_desset_pusher_4")
include(":project_guess_theword_5")
include(":project_sleep_tracker_6_and_7")
