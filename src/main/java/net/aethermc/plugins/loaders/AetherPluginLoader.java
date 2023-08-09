package net.aethermc.plugins.loaders;

import com.esotericsoftware.yamlbeans.YamlReader;
import net.aethermc.Aether;
import net.aethermc.AetherLogger;
import net.aethermc.plugins.AetherPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


// TODO: Make plugin algorithm
public class AetherPluginLoader implements PluginLoader {

    private List<AetherPlugin> getPluginsFile() {
        List<AetherPlugin> plugins = new ArrayList<>();
        File file = new File("./plugins");

        for (File file1 : file.listFiles()) {
            if (file1.isFile() && file1.getName().endsWith(".jar")) {
                AetherPlugin plugin = loadPlugin(file1);
                if (plugin != null) plugins.add(plugin);
            };
        }
        return plugins;
    }

    @Override
    public CompletableFuture<Integer> loadPlugins() {
        return CompletableFuture.supplyAsync(() -> getPluginsFile().size()).exceptionally(throwable -> {
            throwable.printStackTrace();
            return 0;
        });
    }

    @Override
    public CompletableFuture<List<AetherPlugin>> loadAllPlugins() {
        return CompletableFuture.supplyAsync(this::getPluginsFile).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
    }

    @Override
    public @Nullable AetherPlugin loadPlugin(@NotNull File file) {
        try {
            JarFile pluginJar = new JarFile(file);
            if (pluginJar == null) return null;

            Enumeration<JarEntry> entries = pluginJar.entries();
            PluginClassLoader classLoader = new PluginClassLoader();
            List<Class<?>> classes = new ArrayList<>();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();

                if (entry.getName().equals("plugin.yml")) {
                    return loadPluginYML(entry, classes.get(0));
                } else {
                    Class<?> clazz = classLoader.loadClass(entry.getName());
                    if (clazz == null) continue;

                    classes.add(clazz);
                    Aether.getLogger().log(AetherLogger.Level.WARNING, classes.toString());
                }
            }
            classLoader.setClasses(classes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public @Nullable AetherPlugin loadPlugin(AetherPlugin plugin) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public @Nullable AetherPlugin loadPluginYML(@NotNull JarEntry entry, @NotNull Class<?> clazz) {
        try {
            InputStream inputStream = clazz.getResourceAsStream(entry.getName());
            if (inputStream == null) return null;

            YamlReader reader = new YamlReader(new InputStreamReader(inputStream));
            Object object = reader.read();
            Map map = (Map) object;
            Aether.getLogger().log(AetherLogger.Level.WARNING, object.toString());
            Aether.getLogger().log(AetherLogger.Level.WARNING, map.get("main").toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public @NotNull AetherPlugin unloadPlugin(@NotNull AetherPlugin plugin) {
        return null;
    }

    @Override
    public @NotNull AetherPlugin reloadPlugin(@NotNull AetherPlugin plugin) {
        return null;
    }

    @Override
    public @NotNull List<AetherPlugin> getPlugins() {
        return null;
    }

    @Override
    public @NotNull List<AetherPlugin> getEnabledPlugins() {
        return null;
    }

    @Override
    public @NotNull List<AetherPlugin> getDisabledPlugins() {
        return null;
    }
}
