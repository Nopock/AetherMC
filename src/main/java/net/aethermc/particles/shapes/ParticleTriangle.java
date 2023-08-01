package net.aethermc.particles.shapes;

import net.aethermc.particles.ParticleDisplay;
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

public class ParticleTriangle implements ParticleShape {

    private int radius;

    public ParticleTriangle(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public @NotNull CompletableFuture<Void> draw(@NotNull Pos pos, @NotNull ParticleType type, int amount, boolean force, @NotNull Collection<Player> displayPlayers) {
        return CompletableFuture.runAsync(() -> {
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    if (Math.abs(x) + Math.abs(z) <= radius) {
                        double something = radius - Math.max(Math.abs(x), Math.abs(z));
                        double y = pos.y() + something;

                        ParticlePacket packet = type.construct(new Pos(x, y, z));
                        Objects.requireNonNull(packet, "Packet cannot be null");

                        PacketUtils.sendGroupedPacket(displayPlayers, packet);
                    }
                }
            }
        });
    }
}
