[![Atlas banner](https://raw.githubusercontent.com/Mikzyy/Atlas/master/.github/assets/atlas_banner.png)](https://github.com/Mikzyy/Atlas)

Atlas is a lightweight Java utility library for Minecraft server plugins.

<img alt="paper" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/paper_vector.svg">

---

## Installation

### Gradle (Kotlin DSL)
Add the repository and dependency to your `build.gradle.kts`:

```kotlin
repositories {
    mavenCentral()
    maven("https://repo.mikzyy.dev/releases")
}

dependencies {
    implementation("dev.mikzyy:atlas-paper:your-atlas-version")
}
```

### Gradle (Groovy)
Add the repository and dependency to your `build.gradle`:

```groovy
repositories {
    mavenCentral()
    maven { url 'https://repo.mikzyy.dev' }
}

dependencies {
    implementation 'dev.mikzyy:atlas-paper:your-atlas-version'
}
```

### Maven
Add the repository and dependency to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>mikzyy-repo</id>
        <url>https://repo.mikzyy.dev</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>dev.mikzyy</groupId>
        <artifactId>atlas-paper</artifactId>
        <version>your-atlas-version</version>
    </dependency>
</dependencies>
```

> **Tip:** Remember to shade `dev.mikzyy.atlas` into your plugin JAR (e.g., using the Gradle Shadow plugin) so the library is bundled at runtime.

---

## License

This project is licensed under the [MIT License](LICENSE).
