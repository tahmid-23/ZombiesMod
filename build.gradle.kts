plugins {
    kotlin("jvm")
    id("gg.essential.multi-version")
    id("gg.essential.defaults")
}

group = "com.github.tahmid_23.zombiesmod"
version = "0.0.1-SNAPSHOT"

loom {
    if (project.platform.isFabric) {
        accessWidenerPath.set(file("../../src/main/resources/zombiesmod.${project.platform.mcVersionStr}.accesswidener"))
    }
}

preprocess {
    vars.put("FABRIC", if (project.platform.isFabric) 1 else 0)
}

dependencies {
    if (project.platform.isFabric) {
        mapOf(
            11602 to "0.42.0+1.16",
            11802 to "0.58.0+1.18.2"
        )[project.platform.mcVersion]?.let {
            modImplementation("net.fabricmc.fabric-api:fabric-api:$it")
        }
    }
}