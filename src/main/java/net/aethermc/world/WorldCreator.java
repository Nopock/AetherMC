package net.aethermc.world;

import net.aethermc.AetherMC;

@AetherMC(since = "1.0.0")
public interface WorldCreator {


    default Builder builder() {
        return new Builder();
    }

    class Builder {

    }
}
