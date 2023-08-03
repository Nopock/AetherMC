package net.aethermc.events;

import net.minestom.server.entity.Player;
import net.minestom.server.event.trait.BlockEvent;
import net.minestom.server.event.trait.CancellableEvent;
import net.minestom.server.event.trait.PlayerInstanceEvent;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerDiggingEvent implements PlayerInstanceEvent, BlockEvent, CancellableEvent {

    private final Player player;
    private final Status status;
    private final Block block;
    private final BlockFace blockFace;

    private boolean isCancelled = false;

    public PlayerDiggingEvent(@NotNull Player player, @NotNull Status status, @NotNull Block block, @Nullable BlockFace blockFace) {
        this.player = player;
        this.status = status;
        this.block = block;
        this.blockFace = blockFace;
    }

    public @NotNull Status getStatus() {
        return status;
    }

    public @NotNull Block getBlock() {
        return block;
    }

    public @Nullable BlockFace getBlockFace() {
        return blockFace;
    }

    @Override
    public @NotNull Player getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    public enum Status {
        STARTED_DIGGING,
        CANCELLED_DIGGING,
        FINISHED_DIGGING
    }
}
