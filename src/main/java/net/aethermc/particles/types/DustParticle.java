package net.aethermc.particles.types;

import net.aethermc.particles.ParticleType;
import net.minestom.server.color.Color;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.network.packet.server.play.ParticlePacket;
import net.minestom.server.particle.Particle;
import net.minestom.server.particle.ParticleCreator;
import org.jetbrains.annotations.NotNull;

public class DustParticle implements ParticleType {

    private final Particle particle;
    private final Color color;

    public DustParticle(Color color) {
        this(Particle.DUST, color);
    }

    public DustParticle(Particle particle, Color color) {
        this.particle = particle;
        this.color = color;
    }

    public Particle getParticle() {
        return particle;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public ParticlePacket construct(@NotNull Pos displayPosition) {
        return ParticleCreator.createParticlePacket(particle, true, displayPosition.x(), displayPosition.y(), displayPosition.z(),
                0f, 0f, 0f, 0, 1, writer -> {
            writer.writeFloat(color.red() / 255F);
            writer.writeFloat(color.green() / 255F);
            writer.writeFloat(color.blue() / 255F);
            writer.writeFloat(1);
        });
    }
}
