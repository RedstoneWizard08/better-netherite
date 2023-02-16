pluginManagement {
    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }

        maven {
            name = "Quilt"
            url = uri("https://maven.quiltmc.org/repository/release/")
        }

        maven {
            name = "Forge"
            url = uri("https://maven.minecraftforge.net/")
        }

        maven {
            name = "ParchmentMC"
            url = uri("https://maven.parchmentmc.org/")
        }

        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "better-netherite"

include("forge")
include("fabric")
include("quilt")
