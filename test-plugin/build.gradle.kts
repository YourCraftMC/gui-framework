plugins {
    id("com.gradleup.shadow") version "9.0.0-beta4"
}

dependencies {
    implementation(project(":legacy:gui-framework-legacy-bukkit"))
//    implementation(project(":packet:gui-framework-packet-bukkit"))
//    implementation("com.github.retrooper:packetevents-spigot:2.7.0")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}
