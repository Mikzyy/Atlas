package dev.mikzyy.atlas.plugin;

import dev.mikzyy.atlas.config.ConfigManager;
import dev.mikzyy.atlas.menu.MenuListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public abstract class SimplePlugin extends JavaPlugin {

    private ConfigManager configManager;

    @Override
    public void onLoad() {
        onPluginLoad();
    }

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager();
        getServer().getPluginManager().registerEvents(new MenuListener(), this);

        onPluginStart();
    }

    @Override
    public void onDisable() {
        onPluginStop();
    }

    protected void onPluginStart() {

    }

    protected void onPluginStop() {

    }

    protected void onPluginLoad() {

    }
}
