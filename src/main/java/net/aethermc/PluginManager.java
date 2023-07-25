package net.aethermc;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import net.aethermc.plugins.RegisteredPlugin;

@AetherMC(since = "1.0.0")
public class PluginManager {

    public final Cache<String, RegisteredPlugin> cachedPlugins = Caffeine.newBuilder()
            .build();

    public void registerPlugin(RegisteredPlugin plugin) {
        cachedPlugins.put(plugin.getName(), plugin);
    }
}
