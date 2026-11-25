[![Atlas banner](https://raw.githubusercontent.com/Mikzyy/Atlas/master/common/src/main/resources/atlas_banner.png)](https://github.com/Mikzyy/Atlas)

<img alt="paper" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/supported/paper_vector.svg">

A lightweight Java utility library for Minecraft server plugins.

---

## Installation

### Gradle (Kotlin DSL)
Add the repository and dependency to your `build.gradle.kts`:

```kotlin
repositories {
    mavenCentral()
    maven("https://mikzyy.dev")
}

dependencies {
    implementation("dev.mikzyy:atlas-paper:1.0.0+26.3")
}
```

### Gradle (Groovy)
Add the repository and dependency to your `build.gradle`:

```groovy
repositories {
    mavenCentral()
    maven { url 'https://mikzyy.dev' }
}

dependencies {
    implementation 'dev.mikzyy:atlas-paper:1.0.0+26.3'
}
```

### Maven
Add the repository and dependency to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>mikzyy-repo</id>
        <url>https://mikzyy.dev</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>dev.mikzyy</groupId>
        <artifactId>atlas-paper</artifactId>
        <version>1.0.0+26.3</version>
    </dependency>
</dependencies>
```

> **Tip:** Remember to shade `dev.mikzyy.atlas` into your plugin JAR (e.g., using the Gradle Shadow plugin) so the library is bundled at runtime.

---

## Quick Example

```java
import dev.mikzyy.atlas.util.MessageUtil;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

public class Example {
    public void welcome(Player player) {
        MessageUtil.setPrefix("<gradient:#38bdf8:#818cf8><bold>MyServer</bold></gradient>");

        // Send a prefixed MiniMessage with placeholders
        MessageUtil.sendPrefixed(
                player,
                "<gray>Welcome back, <white><player></white>!</gray>",
                Placeholder.unparsed("player", player.getName())
        );
    }
}
```

---

## License

This project is licensed under the [MIT License](LICENSE).
