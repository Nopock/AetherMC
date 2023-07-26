package net.aethermc.chunk;

import net.aethermc.AetherMC;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Chunk;

// TODO: Make sure this class has the correct math
@AetherMC(since = "1.0.0")
public class ChunkBorder {

    public static final int CHUNK_SIZE = 16;
    public static final int MAX_HEIGHT = 256;
    public static final int MIN_HEIGHT = -64;

    private final Chunk chunk;
    private final double minX, minY, minZ;
    private final double maxX, maxY, maxZ;
    private final Pos minPos, maxPos;

    public ChunkBorder(Chunk chunk) {
        this.chunk = chunk;
        this.minX = chunk.getCenterX() - (CHUNK_SIZE / 2);
        this.minY = MIN_HEIGHT;
        this.minZ = chunk.getCenterZ() - (CHUNK_SIZE / 2);
        this.maxX = minX + CHUNK_SIZE;
        this.maxY = MAX_HEIGHT;
        this.maxZ = minZ + CHUNK_SIZE;
        this.minPos = new Pos(minX, minY, minZ);
        this.maxPos = new Pos(maxX, maxY, maxZ);
    }

    public Chunk getChunk() {
        return chunk;
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMinZ() {
        return minZ;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public double getMaxZ() {
        return maxZ;
    }

    public Pos getMinPos() {
        return minPos;
    }

    public Pos getMaxPos() {
        return maxPos;
    }
}
