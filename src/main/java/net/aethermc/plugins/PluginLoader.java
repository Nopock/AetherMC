package net.aethermc.plugins;

import it.unimi.dsi.fastutil.Pair;
import net.aethermc.Aether;
import net.aethermc.AetherMC;
import net.aethermc.PluginManager;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;

@AetherMC(since = "1.0.0")
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public final class PluginLoader {

    private static final Logger LOGGER = Logger.getLogger("AetherMC");

    private static @NotNull Pair<String, String> getData(@NotNull String str) {
        String[] strings = str.split(": ");
        return Pair.of(strings[0], strings[1]);
    }

    private static void getAetherPlugin(@NotNull String value) {
        try {
            Class<?> clazz = Class.forName(value);
            if (!clazz.isAssignableFrom(AetherPlugin.class)) return;

            ((AetherPlugin) clazz.newInstance()).onEnable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("all")
    private static @Nullable RegisteredPlugin getRegisteredPlugin(@NotNull Scanner scanner) {
        String name = null, description = null, version = null, author = null;
        while (scanner.hasNextLine()) {
            Pair<String, String> data = getData(scanner.nextLine());
            String key = data.key();
            String value = data.value();

            switch (key) {
                case "name" -> name = value;
                case "description" -> description = value;
                case "version" -> version = value;
                case "author" -> author = value;
                case "main" -> getAetherPlugin(value);
            }

            if (name != null && description != null && version != null && author != null) {
                return new RegisteredPlugin(name, description, version, author);
            }

            return null;
        }
        return null;
    }

    public synchronized static RegisteredPlugin registerPlugin(File file) {
        final long start = System.currentTimeMillis();
        try {
            JarFile jarFile = new JarFile(file);

            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();
                if (!name.equalsIgnoreCase("plugin.yml")) return null;

                RegisteredPlugin registeredPlugin = getRegisteredPlugin(new Scanner(jarFile.getInputStream(entry)));
                LOGGER.info("Registering plugin " + registeredPlugin.getName() + "...");
                if (registeredPlugin != null) return registeredPlugin;

                Aether.getPluginManager().registerPlugin(registeredPlugin);
                LOGGER.info("Registered plugin " + registeredPlugin.getName() + " in " + (System.currentTimeMillis() - start) + "ms");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CompletableFuture<RegisteredPlugin> registerPluginAsync(File file) {
        return CompletableFuture.supplyAsync(() -> registerPlugin(file)).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
    }
}
