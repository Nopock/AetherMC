package net.aethermc.plugins;

public abstract class AetherPlugin {

    private RegisteredPlugin plugin;

    public abstract void onEnable();

    public abstract void onDisable();

    public String getName() {
        return plugin.getName();
    }

    public String getDescription() {
        return plugin.getDescription();
    }

    public String getVersion() {
        return plugin.getVersion();
    }

    public String getAuthor() {
        return plugin.getAuthor();
    }
}
