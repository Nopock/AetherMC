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

public class ParticleSphere implements ParticleShape {

    private double size;
    public int density;

    public ParticleSphere(double size, int density) {
        this.size = size;
        this.density = density;
    }

    public double getSize() {
        return size;
    }

    public int getDensity() {
        return density;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    @Override
    public @NotNull CompletableFuture<Void> draw(@NotNull Pos pos, @NotNull ParticleType type, int amount, boolean force, @NotNull Collection<Player> displayPlayers) {
        return CompletableFuture.runAsync(() -> {
            for (int x = 0; x < density; x++) {
                for (int z = 0; z < density; z++) {
                    double theta = 2 * Math.PI * x / density;
                    double phi = Math.PI * z / density;

                    double locX = pos.x() + size * Math.sin(phi) * Math.cos(theta);
                    double locY = pos.y() + size * Math.sin(phi) * Math.sin(theta);
                    double locZ = pos.z() + size * Math.cos(phi);

                    ParticlePacket packet = type.construct(new Pos(locX, locY, locZ));
                    Objects.requireNonNull(packet, "Packet cannot be null");

                    PacketUtils.sendGroupedPacket(displayPlayers, packet);
                }
            }
        });
    }
}
