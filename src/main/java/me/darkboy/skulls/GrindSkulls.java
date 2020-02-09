package me.darkboy.skulls;

import me.darkboy.skulls.commands.MainCommand;
import me.darkboy.skulls.guis.ItemGUI;
import me.darkboy.skulls.listeners.ChatListener;
import me.darkboy.skulls.skulls.SkullManager;
import me.darkboy.skulls.system.OmegaPlugin;
import me.darkboy.skulls.system.resource.TemporaryConfig;
import org.bukkit.Bukkit;

public final class GrindSkulls extends OmegaPlugin {

    private static GrindSkulls plugin;
    private static TemporaryConfig configuration;
    private static SkullManager skullManager;

    @Override
    public void enable() {
        // Plugin startup logic
        plugin = this;

        configuration = new TemporaryConfig("plugins/GrindSkulls", "Config.yml");
        configuration.saveConfig();

        skullManager = new SkullManager();

        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new ItemGUI(), this);

        this.register(MainCommand.class);
    }

    @Override
    public void disable() {
        // Plugin shutdown logic

    }

    public static GrindSkulls getPlugin() {
        return plugin;
    }

    public static TemporaryConfig getConfiguration() {
        return configuration;
    }

    public static SkullManager getSkullManager() {
        return skullManager;
    }
}
