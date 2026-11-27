plugins {
    `java-library`
    `maven-publish`
    id("io.freefair.lombok")
}

version = "1.0.0"

dependencies {
    compileOnlyApi("org.jetbrains:annotations:26.0.1")

    compileOnlyApi("net.kyori:adventure-api:4.21.0") {
        exclude(module = "adventure-bom")
        exclude(module = "checker-qual")
        exclude(module = "annotations")
    }

    compileOnlyApi("net.kyori:adventure-text-serializer-legacy:4.21.0") {
        exclude(module = "adventure-bom")
        exclude(module = "adventure-api")
    }

    compileOnlyApi("net.kyori:adventure-text-serializer-plain:4.21.0") {
        exclude(module = "adventure-bom")
        exclude(module = "adventure-api")
    }

    compileOnlyApi("net.kyori:adventure-text-minimessage:4.21.0") {
        exclude(module = "adventure-bom")
        exclude(module = "adventure-api")
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "atlas-common"
            from(components["java"])
            pom {
                name.set("Atlas common module")
                description.set("Common implementation for Atlas.")
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
    archiveFileName.set("Atlas-${project.version}-common.jar")
    from(rootProject.file("LICENSE")) {
        rename { "${it}_Atlas" }
    }
}
