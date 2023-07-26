package net.aethermc.world;

import net.aethermc.AetherMC;
import net.aethermc.region.Region;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.block.Block;

import java.util.ArrayList;
import java.util.List;

@AetherMC(since = "1.0.0")
public class AetherWorld {

    private final String name;
    private final WorldProperties properties;

    public AetherWorld(String name, WorldProperties properties) {
        this.name = name;
        this.properties = properties;
    }

    public Region getRegion(int x, int y, int z) {
        for (Region region : properties.getRegionsInWorld()) {
            if (region.isWithinRegion(x, y, z))
                return region;
        }
        return null;
    }

    public Block getBlockAt(int x, int y, int z) {
        return properties.getBlockAt(x, y, z);
    }

    public void setBlockAt(int x, int y, int z, Block block) {
        properties.setBlockAt(x, y, z, block);
    }

    public String getName() {
        return name;
    }

    public WorldProperties getProperties() {
        return properties;
    }
}
