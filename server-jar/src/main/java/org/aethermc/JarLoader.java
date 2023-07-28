package org.aethermc;

import net.aethermc.Aether;
import net.aethermc.AetherLogger;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JarLoader {


    public static void checkFiles() {
        Aether.getLogger().log(AetherLogger.Level.INFO, "Initializing AetherMC...");
        List<String> files = new ArrayList<>(Arrays.asList("plugins", "logs", "cache", "worlds", "libraries"));

        for (String name : files) {
            final File file = new File(name);
            if (!file.exists() && !file.mkdir())
                Aether.getLogger().log(AetherLogger.Level.SEVERE, "There was an error creating the " + name + " folder!");
        }
    }
}
