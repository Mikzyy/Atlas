package dev.mikzyy.atlas.config;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

@Getter
public abstract class SimpleConfig {

    protected final JavaPlugin plugin;
    protected final String fileName;
    protected final File file;
    protected FileConfiguration config;

    public SimpleConfig(final @NotNull JavaPlugin plugin, final @NotNull String fileName) {
        this.plugin = plugin;
        this.fileName = fileName.endsWith(".yml") ? fileName : fileName + ".yml";
        this.file = new File(plugin.getDataFolder(), this.fileName);

        this.reload();
    }

    public final void reload() {
        createIfNotExists();
        this.config = YamlConfiguration.loadConfiguration(this.file);

        try (InputStream defaultStream = this.plugin.getResource(this.fileName)) {
            if (defaultStream != null) {
                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(
                        new InputStreamReader(defaultStream, StandardCharsets.UTF_8)
                );
                this.config.setDefaults(defaultConfig);
            }
        } catch (IOException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Failed to load defaults for " + this.fileName, e);
        }

        onReload();
    }

    public void save() {
        if (this.config == null) {
            return;
        }
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.fileName, e);
        }
    }

    protected void onReload() {
    }

    private void createIfNotExists() {
        if (this.file.exists()) {
            return;
        }

        File parent = this.file.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            this.plugin.getLogger().warning("Failed to create directory: " + parent.getPath());
        }

        if (this.plugin.getResource(this.fileName) != null) {
            this.plugin.saveResource(this.fileName, false);
        } else {
            try {
                if (!this.file.createNewFile()) {
                    this.plugin.getLogger().warning("Could not create new file: " + this.fileName);
                }
            } catch (IOException e) {
                this.plugin.getLogger().log(Level.SEVERE, "Failed to create " + this.fileName, e);
            }
        }
    }
}
