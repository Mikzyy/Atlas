plugins {
    `java-library`
    `maven-publish`
    id("io.freefair.lombok")
}

version = "1.0.0+26.3"

sourceSets {
    main {
        java.srcDirs(
            project(":common").file("src/main/java"),
            file("src/main/java")
        )
        resources.srcDirs(
            project(":common").file("src/main/resources"),
            file("src/main/resources")
        )
    }
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.3.build.+")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "atlas-paper"
            from(components["java"])
            pom {
                name.set("Atlas for Paper")
                description.set("Paper implementation for Atlas.")
                url.set("https://github.com/Mikzyy/Atlas")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org")
                    }
                }
                developers {
                    developer {
                        id.set("mikzyy")
                        name.set("mikzyy")
                    }
                }
                scm {
                    url.set("https://github.com/Mikzyy/Atlas")
                    connection.set("scm:git:https://github.com/Mikzyy/Atlas.git")
                    developerConnection.set("scm:git:ssh://git@github.com/Mikzyy/Atlas.git")
                }
            }
        }
    }
}

tasks.named<Jar>("jar") {
    archiveFileName.set("Atlas-${project.version}-paper.jar")
    from(rootProject.file("LICENSE")) {
        rename { "${it}_Atlas" }
    }
}
