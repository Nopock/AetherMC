package net.aethermc.particles.types;

import net.aethermc.particles.ParticleType;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.network.packet.server.play.ParticlePacket;
import net.minestom.server.particle.Particle;
import net.minestom.server.particle.ParticleCreator;
import org.jetbrains.annotations.NotNull;

public class NormalParticle implements ParticleType {

    private final Particle particle;

    public NormalParticle(Particle particle) {
        this.particle = particle;
    }

    public Particle getParticle() {
        return particle;
    }

    @Override
    public ParticlePacket construct(@NotNull Pos displayPosition) {
        return ParticleCreator.createParticlePacket(particle, displayPosition.x(), displayPosition.y(), displayPosition.z(), 0f, 0f, 0f, 1);
    }
}
