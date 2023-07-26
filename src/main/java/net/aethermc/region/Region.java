package net.aethermc.region;

import net.aethermc.AetherMC;
import net.aethermc.world.AetherWorld;
import net.minestom.server.collision.BoundingBox;

@AetherMC(since = "1.0.0")
public class Region {

    private final String name;
    private final RegionBox regionBox;
    private final AetherWorld world;

    public Region(String name, RegionBox regionBox, AetherWorld world) {
        this.name = name;
        this.regionBox = regionBox;
        this.world = world;
    }

    public boolean isWithinRegion(int x, int y, int z) {
        return regionBox.isWithinRegion(x, y, z);
    }

    public String getName() {
        return name;
    }

    public RegionBox getRegionBox() {
        return regionBox;
    }

    public AetherWorld getWorld() {
        return world;
    }
}
