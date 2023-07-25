package net.aethermc.plugins;

import net.aethermc.AetherMC;

@AetherMC(since = "1.0.0")
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
