package net.aethermc.plugins.loaders;

import net.aethermc.AetherMC;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@AetherMC(since = "1.0.0-snapshot.4")
public class PluginClassLoader implements ClassLoader {

    private List<Class<?>> classes = new ArrayList<>();

    public List<Class<?>> getClasses() {
        return classes;
    }

    public void setClasses(List<Class<?>> classes) {
        this.classes = classes;
    }
    public @Nullable Class<?> loadClass(@NotNull String fileName) {
        try {
            return Class.forName(fileName);
        } catch (ClassNotFoundException e) {
            e.fillInStackTrace();
            return null;
        }
    }
}
