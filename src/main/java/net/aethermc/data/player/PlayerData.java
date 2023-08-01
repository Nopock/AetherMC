package net.aethermc.data.player;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import net.minestom.server.coordinate.Pos;

import java.io.Serializable;
import java.util.UUID;

public final class PlayerData implements Data, Serializable {

    private final UUID uuid;
    private final Pos lastPosition;
    private final boolean isOp;

    public PlayerData(UUID uuid, Pos lastPosition, boolean isOp) {
        this.uuid = uuid;
        this.lastPosition = lastPosition;
        this.isOp = isOp;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Pos getLastPosition() {
        return lastPosition;
    }

    public boolean isOp() {
        return isOp;
    }

}
