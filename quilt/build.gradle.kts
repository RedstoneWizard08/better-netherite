plugins {
    id("org.quiltmc.loom") version "1.0.+"
}

dependencies {
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")
	mappings("org.quiltmc:quilt-mappings:${property("quilt_mappings")}:intermediary-v2")

    modImplementation("org.quiltmc:quilt-loader:${property("quilt_loader_version")}")
    modImplementation("org.quiltmc.quilted-fabric-api:quilted-fabric-api:${property("quilted_fabric_api")}")
}

tasks.withType<JavaCompile> {
	options.release.set(17)
}

tasks {
	processResources {
		inputs.property("version", project.version)

		filesMatching("quilt.mod.json") {
			expand(mutableMapOf("version" to project.version))
		}
	}

	java {
		withSourcesJar()
	}

	jar {
		from("LICENSE")

		archiveClassifier.set("quilt")
	}
}
