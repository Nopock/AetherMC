package net.aethermc.plugins;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.concurrent.CompletableFuture;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginLoader {

    public synchronized static RegisteredPlugin registerPlugin(File file) {
        try {
            JarFile jarFile = new JarFile(file);
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().equals("plugin.yml")) {
                   try (InputStream inputStream = jarFile.getInputStream(entry)) {

                   }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CompletableFuture<RegisteredPlugin> registerPluginAsync(File file) {
        return CompletableFuture.supplyAsync(() -> registerPlugin(file));
    }
}
