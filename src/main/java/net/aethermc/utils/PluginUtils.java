package net.aethermc.utils;

import net.aethermc.AetherMC;
import net.aethermc.plugins.AetherPlugin;
import net.aethermc.plugins.loaders.AetherPluginLoader;
import net.aethermc.plugins.PluginSort;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@AetherMC(since = "1.0.0-snapshot.4")
public class PluginUtils {

    protected static final AetherPluginLoader aetherPluginLoader = new AetherPluginLoader();

    public static CompletableFuture<Integer> loadPlugins() {
        return CompletableFuture.completedFuture(0);
    }

    public static void loadPlugin(@NotNull AetherPlugin plugin) {
        if (!plugin.enabled)
            aetherPluginLoader.loadPlugin(plugin);
    }

    public static void unloadPlugin(@NotNull AetherPlugin plugin) {
        if (plugin.enabled)
            aetherPluginLoader.unloadPlugin(plugin);
    }
    public static void reloadPlugin(@NotNull AetherPlugin plugin) {
        if (plugin.enabled && plugin.loaded)
            aetherPluginLoader.reloadPlugin(plugin);
    }
}
