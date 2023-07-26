package net.aethermc.coordinate;

import net.aethermc.AetherMC;
import net.aethermc.region.Region;
import net.aethermc.world.AetherWorld;
import net.minestom.server.coordinate.Pos;

import java.util.Optional;

@AetherMC(since = "1.0.0")
public class Location {

    private final int x, y, z;
    private final float yaw, pitch;
    private final AetherWorld world;

    public Location(int x, int y, int z, float yaw, float pitch, AetherWorld world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    public Location(int x, int y, int z, AetherWorld world) {
        this(x, y, z, 0, 0, world);
    }

    public Location(Pos pos, AetherWorld world) {
        this((int) pos.x(), (int) pos.y(), (int) pos.z(), world);
    }

    public boolean isInWorld(AetherWorld world) {
        return this.world.equals(world);
    }

    public Optional<Region> getRegion() {
        return Optional.of(world.getRegion(x, y, z));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public AetherWorld getWorld() {
        return world;
    }
}
