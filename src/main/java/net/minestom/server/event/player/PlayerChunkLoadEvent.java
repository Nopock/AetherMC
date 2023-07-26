package net.minestom.server.event.player;

import net.aethermc.AetherMC;
import net.minestom.server.entity.Player;
import net.minestom.server.event.trait.PlayerInstanceEvent;
import net.minestom.server.instance.Chunk;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a player receive a new chunk data.
 */
public class PlayerChunkLoadEvent implements PlayerInstanceEvent {

    private final Player player;
    private final int chunkX, chunkZ;

    public PlayerChunkLoadEvent(@NotNull Player player, int chunkX, int chunkZ) {
        this.player = player;
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
    }

    /**
     * Gets the chunk X.
     *
     * @return the chunk X
     */
    public int getChunkX() {
        return chunkX;
    }

    /**
     * Gets the chunk Z.
     *
     * @return the chunk Z
     */
    public int getChunkZ() {
        return chunkZ;
    }

    @AetherMC(since = "1.0.0")
    public Chunk getChunk() {
        return player.getInstance().getChunk(chunkX, chunkZ);
    }

    @Override
    public @NotNull Player getPlayer() {
        return player;
    }
}
