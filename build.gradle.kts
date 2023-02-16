plugins {
    id("java")
	id("eclipse")
    id("maven-publish")
}

subprojects {
    apply(plugin = "java")
	apply(plugin = "eclipse")
    apply(plugin = "maven-publish")

    version = property("mod_version")!! as String
    group = property("maven_group")!! as String

	val archivesBaseName = property("archives_base_name")!! as String
	val minecraftVersion = property("mod_version")!! as String

	tasks.jar {
		archiveBaseName.set(archivesBaseName)
		archiveVersion.set("v${project.version}+mc${minecraftVersion}")
	}

	repositories {
		maven {
            name = "ParchmentMC"
            url = uri("https://maven.parchmentmc.org/")
        }
	}

    publishing {
    	publications {
    		create<MavenPublication>("mavenJava") {
    			groupId = property("maven_group")!! as String
    			artifactId = property("archives_base_name")!! as String
    			version = property("mod_version")!! as String

    			from(components["java"])
    		}
    	}

    	repositories {}
    }
}
