pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://pkgs.dev.azure.com/CS-Package/ID-Lab-PackagesSDK/_packaging/packages/maven/v1")
            name = "packages"
            credentials {
                username = "CS-Packages"
                password = "ADICIONE SEU PAT AQUI"
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
}

rootProject.name = "LeevoAndroid"
include(":app")
 