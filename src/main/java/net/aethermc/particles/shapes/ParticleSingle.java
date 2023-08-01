package net.aethermc.particles.shapes;

import net.aethermc.particles.ParticleShape;
import net.aethermc.particles.ParticleType;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.network.packet.server.play.ParticlePacket;
import net.minestom.server.utils.PacketUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class ParticleSingle implements ParticleShape {

    @Override
    public @NotNull CompletableFuture<Void> draw(@NotNull Pos pos, @NotNull ParticleType type, int amount, boolean force, @NotNull Collection<Player> displayPlayers) {
        return CompletableFuture.supplyAsync(() -> {
            ParticlePacket packet = type.construct(pos);
            Objects.requireNonNull(packet, "Packet cannot be null");

            PacketUtils.sendGroupedPacket(displayPlayers, packet);
            return null;
        });
    }
}
