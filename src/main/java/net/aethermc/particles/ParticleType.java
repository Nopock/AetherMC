package net.aethermc.particles;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.network.packet.server.play.ParticlePacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ParticleType {

    @Nullable ParticlePacket construct(@NotNull Pos displayPosition);
}
