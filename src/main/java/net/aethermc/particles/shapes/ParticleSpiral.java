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

public class ParticleSpiral implements ParticleShape {

    private int size;
    private double density;

    public ParticleSpiral(int size, double density) {
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
            double totalRadians = 5 * Math.PI;

            for (double radians = 0; radians < totalRadians; radians += density) {
                double x = pos.x() + size * Math.cos(radians);
                double y = pos.y() + 2 * radians;
                double z = pos.z() + size * Math.sin(radians);

                ParticlePacket packet = type.construct(new Pos(x, y, z));
                Objects.requireNonNull(packet, "Packet cannot be null");

                PacketUtils.sendGroupedPacket(displayPlayers, packet);
            }
        });
    }
}
