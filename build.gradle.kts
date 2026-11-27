import org.gradle.authentication.http.BasicAuthentication

plugins {
    `java-library`
    `maven-publish`
    id("io.freefair.lombok") version "9.8.3" apply false
}

val libGroup = "dev.mikzyy"
val libVersion = "1.0.0"

configure(subprojects) {
    group = libGroup
    version = libVersion

    repositories {
        mavenCentral()
    }

    plugins.withId("java") {
        extensions.configure<JavaPluginExtension> {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(25))
            }
            withSourcesJar()
        }
    }

    plugins.withId("maven-publish") {
        extensions.configure<PublishingExtension> {
            repositories {
                maven {
                    name = "Mikzyy"
                    url = uri(
                        providers.gradleProperty("atlasMavenUrl")
                            .orElse("https://repo.mikzyy.dev/releases")
                            .get()
                    )
                    if (url.scheme != "file") {
                        credentials {
                            username = providers.environmentVariable("MIKZYY_MAVEN_USER").orNull
                            password = providers.environmentVariable("MIKZYY_MAVEN_TOKEN").orNull
                        }
                        authentication {
                            create<BasicAuthentication>("basic")
                        }
                    }
                }
            }
        }
    }
}
