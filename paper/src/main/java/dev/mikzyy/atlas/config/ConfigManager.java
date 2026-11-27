package dev.mikzyy.atlas.config;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private final Map<Class<? extends SimpleConfig>, SimpleConfig> configs = new HashMap<>();

    public void registerConfig(final @NotNull SimpleConfig config) {
        this.configs.put(config.getClass(), config);
    }

    public <T extends SimpleConfig> @Nullable T getConfig(final @NotNull Class<T> clazz) {
        return clazz.cast(this.configs.get(clazz));
    }

    public void reloadAll() {
        for (SimpleConfig config : this.configs.values()) {
            config.reload();
        }
    }

    public @NotNull Collection<SimpleConfig> getConfigs() {
        return Collections.unmodifiableCollection(this.configs.values());
    }
}