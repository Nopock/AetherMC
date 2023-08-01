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

public class ParticleLine implements ParticleShape {

    private Pos from;
    private Pos to;
    private int totalParticles;
    private double density;

    public ParticleLine(Pos from, Pos to) {
        this(from, to, 1, 1);
    }

    public ParticleLine(Pos from, Pos to, int totalParticles) {
        this(from, to, totalParticles, 1);
    }

    public ParticleLine(Pos from, Pos to, int totalParticles, double density) {
        this.from = from;
        this.to = to;
        this.totalParticles = totalParticles;
        this.density = density;
    }

    public Pos getFrom() {
        return from;
    }

    public Pos getTo() {
        return to;
    }

    public int getTotalParticles() {
        return totalParticles;
    }

    public double getDensity() {
        return density;
    }

    public void setFrom(Pos from) {
        this.from = from;
    }

    public void setTo(Pos to) {
        this.to = to;
    }

    public void setTotalParticles(int totalParticles) {
        this.totalParticles = totalParticles;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    @Override
    public @NotNull CompletableFuture<Void> draw(@NotNull Pos pos, @NotNull ParticleType type, int amount, boolean force, @NotNull Collection<Player> displayPlayers) {
        return CompletableFuture.runAsync(() -> {
            for (double i = 0; i < totalParticles; i += density) {
                double ratio = i / totalParticles;
                double x = from.x() + (to.x() - from.x()) * ratio;
                double y = from.y() + (to.y() - from.y()) * ratio;
                double z = from.z() + (to.z() - from.z()) * ratio;

                ParticlePacket packet = type.construct(new Pos(x, y, z));
                Objects.requireNonNull(packet, "Packet cannot be null");

                PacketUtils.sendGroupedPacket(displayPlayers, packet);
            }
        });
    }
}
