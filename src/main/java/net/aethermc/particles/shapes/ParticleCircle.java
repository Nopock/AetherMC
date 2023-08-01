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

public class ParticleCircle implements ParticleShape {

    private int size;
    private double density;

    public ParticleCircle(int size, double density) {
        this.size = size;
        this.density = density;
    }

    public int getSize() {
        return size;
    }

    public double getDensity() {
        return density;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    @Override
    public @NotNull CompletableFuture<Void> draw(@NotNull Pos pos, @NotNull ParticleType type, int amount, boolean force, @NotNull Collection<Player> displayPlayers) {
        return CompletableFuture.runAsync(() -> {
            double angleIncrement = 2 * Math.PI / density;
            for (int i = 0; i < density; i++) {
                double angle = i * angleIncrement;
                double x = pos.x() + size * Math.cos(angle);
                double z = pos.z() + size * Math.sin(angle);

                ParticlePacket packet = type.construct(new Pos(x, pos.y(), z));
                Objects.requireNonNull(packet, "Packet cannot be null");

                PacketUtils.sendGroupedPacket(displayPlayers, packet);
            }
        });
    }
}
