package net.aethermc.particles.types;

import net.aethermc.particles.ParticleType;
import net.minestom.server.color.Color;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.network.packet.server.play.ParticlePacket;
import net.minestom.server.particle.Particle;
import net.minestom.server.particle.ParticleCreator;
import org.jetbrains.annotations.NotNull;

public class ParticleTransition implements ParticleType {

    private final Particle particle;
    private final Color from;
    private final Color to;

    public ParticleTransition(Particle particle, Color from, Color to) {
        this.particle = particle;
        this.from = from;
        this.to = to;
    }

    public ParticleTransition(Color from, Color to) {
        this(Particle.DUST_COLOR_TRANSITION, from, to);
    }

    public Particle getParticle() {
        return particle;
    }

    public Color getFrom() {
        return from;
    }

    public Color getTo() {
        return to;
    }

    @Override
    public ParticlePacket construct(@NotNull Pos displayPosition) {
        return ParticleCreator.createParticlePacket(particle, true, displayPosition.x(), displayPosition.y(), displayPosition.z(),
                0f, 0f, 0f, 0, 1, writer -> {
            writer.writeFloat(from.red() / 255F);
            writer.writeFloat(from.green() / 255F);
            writer.writeFloat(from.blue() / 255F);

            writer.writeFloat(1);

            writer.writeFloat(to.red() / 255F);
            writer.writeFloat(to.green() / 255F);
            writer.writeFloat(to.blue() / 255F);
        });
    }
}
