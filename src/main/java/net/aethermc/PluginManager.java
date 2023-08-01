package net.aethermc;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import net.aethermc.plugins.PluginLoader;
import net.aethermc.plugins.RegisteredPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@AetherMC(since = "1.0.0")
public class PluginManager {

    public final Cache<String, RegisteredPlugin> cachedPlugins = Caffeine.newBuilder()
            .weakValues()
            .build();

    public void registerPlugin(RegisteredPlugin plugin) {
        cachedPlugins.put(plugin.getName(), plugin);
    }

    public CompletableFuture<Integer> registerAllPlugins() {
        return CompletableFuture.supplyAsync(() -> {
            File[] files = Arrays.stream(new File("plugins").listFiles()).filter(file -> file.getName().endsWith(".jar")).toArray(File[]::new);
            for (File file : files)
                PluginLoader.registerPlugin(file);

            return files.length;
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            return -1;
        });
    }
}
