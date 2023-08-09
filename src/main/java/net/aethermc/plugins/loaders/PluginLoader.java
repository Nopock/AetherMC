package net.aethermc.plugins.loaders;

import net.aethermc.AetherMC;
import net.aethermc.plugins.AetherPlugin;
import net.aethermc.plugins.Plugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.jar.JarEntry;

@AetherMC(since = "1.0.0-snapshot.4")
public interface PluginLoader {

    CompletableFuture<Integer> loadPlugins();

    CompletableFuture<List<AetherPlugin>> loadAllPlugins();

    @Nullable AetherPlugin loadPlugin(@NotNull File file);

    @Contract("null -> null")
    @Nullable AetherPlugin loadPlugin(AetherPlugin plugin);

    @Nullable AetherPlugin loadPluginYML(@NotNull JarEntry entry, @NotNull Class<?> clazz);

    @Contract(pure = true)
    @NotNull AetherPlugin unloadPlugin(@NotNull AetherPlugin plugin);

    @Contract(pure = true)
    @NotNull AetherPlugin reloadPlugin(@NotNull AetherPlugin plugin);

    @NotNull List<AetherPlugin> getPlugins();

    @NotNull List<AetherPlugin> getEnabledPlugins();

    @NotNull List<AetherPlugin> getDisabledPlugins();
}
