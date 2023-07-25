package net.aethermc;

@AetherMC(since = "1.0.0")
public class Aether {

    public static PluginManager getPluginManager() {
        return new PluginManager();
    }
}
