package net.aethermc.world;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import net.aethermc.AetherMC;

import java.util.Collection;

// TODO: Finish this
@AetherMC(since = "1.0.0")
public class WorldLoader {

    public static Cache<String, AetherWorld> cachedWorlds = Caffeine.newBuilder().build();

    public static AetherWorld loadWorld(String name) {
        return null;
    }

    public static Collection<AetherWorld> loadAllWorlds() {
        return null;
    }

    public static AetherWorld constructWorld(WorldCreator creator) {
        return null;
    }
}
