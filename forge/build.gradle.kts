plugins {
    id("net.minecraftforge.gradle") version "5.1.+"
    id("org.parchmentmc.librarian.forgegradle") version "1.+"
}

minecraft {
    mappings("parchment", "${property("parchment_mappings")}-${property("minecraft_version")}")

    // accessTransformer = file("src/main/resources/META-INF/accesstransformer.cfg")

    runs {
        create("client") {
            workingDirectory(project.file("run"))

            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            property("forge.enabledGameTestNamespaces", "examplemod")
        }

        create("server") {
            workingDirectory(project.file("run"))

            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            property("forge.enabledGameTestNamespaces", "examplemod")
        }

        create("gameTestServer") {
            workingDirectory(project.file("run"))

            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            property("forge.enabledGameTestNamespaces", "examplemod")
        }

        create("data") {
            workingDirectory(project.file("run"))

            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")

            args(
                "--mod",
                "examplemod",
                "--all",
                "--output",
                file("src/generated/resources/"),
                "--existing",
                file("src/main/resources/")
            )
        }
    }
}

repositories {
}

dependencies {
    minecraft("net.minecraftforge:forge:1.19.3-44.1.0")
}

tasks.jar {
    archiveClassifier.set("forge")
    
    finalizedBy("reobfJar")
}
