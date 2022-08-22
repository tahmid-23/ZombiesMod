plugins {
    kotlin("jvm") version "1.6.20" apply false
    id("gg.essential.multi-version.root")
    id("gg.essential.multi-version.api-validation")
}

preprocess {
    val fabric11802 = createNode("1.18.2-fabric", 11802, "yarn")
    val fabric11602 = createNode("1.16.2-fabric", 11602, "yarn")
    val forge11602 = createNode("1.16.2-forge", 11602, "mcp")
    val forge10809 = createNode("1.8.9-forge", 10809, "mcp")

    fabric11802.link(fabric11602, file("/versions/1.18.2-fabric/mapping.txt"))
    fabric11602.link(forge11602)
    forge11602.link(forge10809, file("/versions/1.16.2-forge/mapping.txt"))
}