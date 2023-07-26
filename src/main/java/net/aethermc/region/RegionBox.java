package net.aethermc.region;

import com.github.benmanes.caffeine.cache.Cache;
import net.aethermc.coordinate.Location;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.Material;

import java.util.ArrayList;
import java.util.List;

public class RegionBox {

    public static final List<RegionBox> registeredBoxes = new ArrayList<>();

    private final int minX, minY, minZ;
    private final int maxX, maxY, maxZ;

    public RegionBox(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;

        registeredBoxes.add(this);
    }

    public boolean isWithinRegion(double x, double y, double z) {
        return x >= minX && x <= maxX && y >= minY && y <= maxY && z >= minZ && z <= maxZ;
    }

    public boolean isWithinRegion(Pos pos) {
        return isWithinRegion(pos.x(), pos.y(), pos.z());
    }

    public boolean isWithinRegion(Location location) {
        return isWithinRegion(location.getX(), location.getY(), location.getZ());
    }

    public List<Block> getBlocks() {
        return new ArrayList<>();
    }

    public int getBlockCount(Material material) {
        return 0;
    }

    public int getVolume() {
        return (maxX - minX) * (maxY - minY) * (maxZ - minZ);
    }

    public int getArea() {
        return (maxX - minX) * (maxZ - minZ);
    }

    public int getPerimeter() {
        return (maxX - minX) * 2 + (maxZ - minZ) * 2;
    }

    public int getLength() {
        return maxX - minX;
    }

    public int getWidth() {
        return maxZ - minZ;
    }

    public int getHeight() {
        return maxY - minY;
    }

    public int getCenterX() {
        return (maxX - minX) / 2;
    }

    public int getCenterY() {
        return (maxY - minY) / 2;
    }

    public int getCenterZ() {
        return (maxZ - minZ) / 2;
    }

    public Pos getCenter() {
        return new Pos(getCenterX(), getCenterY(), getCenterZ());
    }

    public Pos getMin() {
        return new Pos(minX, minY, minZ);
    }

    public Pos getMax() {
        return new Pos(maxX, maxY, maxZ);
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMinZ() {
        return minZ;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMaxZ() {
        return maxZ;
    }
}
