plugins {
	id("fabric-loom") version "1.1-SNAPSHOT"
}

dependencies {
	minecraft("com.mojang:minecraft:${property("minecraft_version")}")
	mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")

	modImplementation("net.fabricmc:fabric-loader:${property("fabric_loader_version")}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")
}

tasks.withType<JavaCompile> {
	options.release.set(17)
}

tasks {
	processResources {
		inputs.property("version", project.version)

		filesMatching("fabric.mod.json") {
			expand(mutableMapOf("version" to project.version))
		}
	}

	java {
		withSourcesJar()
	}

	jar {
		from("LICENSE")

		archiveClassifier.set("fabric")
	}
}

modrinth {
	token.set(System.getenv("MODRINTH_TOKEN")!! as String)
	projectId.set("better-netherite")
	versionType.set("alpha")
	uploadFile.set(tasks.remapJar as Any?)
	gameVersions.add(property("minecraft_version")!! as String)
	loaders.add("fabric")

	if (System.getenv("GITHUB_ACTIONS") == null) {
		versionNumber.set("fabric-v${property("mod_version")}+mc${property("minecraft_version")}")
	} else {
		val commit = (System.getenv("GITHUB_SHA")!! as String).substring(0, 7)
		versionNumber.set("fabric-v${property("mod_version")}+mc${property("minecraft_version")}+${commit}")
	}

	dependencies {
		required.project("fabric-api")
	}
}
