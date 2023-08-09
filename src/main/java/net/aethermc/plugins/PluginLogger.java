package net.aethermc.plugins;

import net.aethermc.AetherLogger;
import net.aethermc.AetherMC;
import org.jetbrains.annotations.NotNull;

@AetherMC(since = "1.0.0-snapshot.4")
public class PluginLogger extends AetherLogger {

    public PluginLogger(@NotNull String prefix) {
        super(prefix);
    }
}
