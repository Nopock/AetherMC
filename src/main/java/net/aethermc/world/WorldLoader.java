package net.aethermc.world;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import net.aethermc.AetherMC;
import net.aethermc.Timer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.logging.Logger;

// TODO: Finish this
@AetherMC(since = "1.0.0")
public class WorldLoader {

    public static Logger LOGGER = Logger.getLogger("AetherMC");
    public static Cache<String, AetherWorld> cachedWorlds = Caffeine.newBuilder().build();

    public static @Nullable AetherWorld loadWorld(@NotNull String name) {
        AetherWorld world = null;
        long timeTaken = new Timer().start(() -> {

        }).get();
        LOGGER.info("Finished loading world " + name + " in " + timeTaken + "ms");
        return world;
    }

    public static Collection<AetherWorld> loadAllWorlds() {
        return null;
    }

    public static AetherWorld constructWorld(WorldCreator creator) {
        return null;
    }
}
