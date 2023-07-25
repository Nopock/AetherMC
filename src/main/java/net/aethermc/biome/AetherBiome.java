package net.aethermc.biome;

import net.aethermc.AetherMC;

@AetherMC(since = "1.0.0")
public interface AetherBiome {

    default Builder builder() {
        return new Builder();
    }

    class Builder {

    }
}
