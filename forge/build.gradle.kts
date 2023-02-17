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

modrinth {
	token.set(System.getenv("MODRINTH_TOKEN")!! as String)
	projectId.set("better-netherite")
	versionType.set("alpha")
	uploadFile.set(tasks.remapJar as Any?)
	gameVersions.add(property("minecraft_version")!! as String)
	loaders.add("forge")

	if (System.getenv("GITHUB_ACTIONS") == null) {
		versionNumber.set("forge-v${property("mod_version")}+mc${property("minecraft_version")}")
	} else {
		val commit = (System.getenv("GITHUB_SHA")!! as String).substring(0, 7)
		versionNumber.set("forge-v${property("mod_version")}+mc${property("minecraft_version")}+${commit}")
	}
}
