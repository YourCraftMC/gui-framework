plugins {
    `java-library`
    `kotlin-dsl`
}

allprojects {
    apply(plugin = "java-library")

    repositories {
        mavenLocal()
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://repo1.maven.org/maven2/")
        maven("https://repo.maven.apache.org/maven2/")
        maven("https://repo.codemc.io/repository/nms/")
        maven("https://repo.codemc.io/repository/maven-releases/")
    }

    group = "cn.ycraft.lib"
    version = "1.0.0"

    java {
        withSourcesJar()
        withJavadocJar()
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<JavaCompile>() {
        options.encoding = "UTF-8"
    }

    tasks.withType<Javadoc>() {
        options.encoding = "UTF-8"
        if (JavaVersion.current().isJava9Compatible) {
            (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
        }
    }

}