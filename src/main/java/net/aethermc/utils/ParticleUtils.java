package net.aethermc.utils;

import net.aethermc.particles.ParticleDisplay;
import net.aethermc.particles.ParticleShape;
import net.aethermc.particles.ParticleType;
import net.aethermc.particles.shapes.ParticleSingle;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParticleUtils {

    @Contract(pure = true)
    public static ParticleDisplay createSingle(ParticleType type, int amount, boolean force) {
        return new ParticleDisplay(List.of(new ParticleSingle()), type, amount, force);
    }

    @Contract(pure = true)
    public static ParticleDisplay createSingle(ParticleType type, boolean force) {
        return createSingle(type, -1, force);
    }

    @Contract(value = "-> new", pure = true)
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final List<ParticleShape> shapes = new ArrayList<>();
        private int amount = -1;
        private boolean force = true;
        private ParticleType type = null;

        @Contract(value = "_ -> this", pure = true)
        public Builder setAmount(int amount) {
            this.amount = amount;
            return this;
        }


        @Contract("_, null -> fail")
        public Builder addShape(ParticleShape shape, ParticleType type) {
            if (shape == null)
                shape = new ParticleSingle();
            shapes.add(shape);

            Objects.requireNonNull(type);

            this.type = type;
            return this;
        }

        @Contract(pure = true)
        public Builder setForce(boolean force) {
            this.force = force;
            return this;
        }

        @Contract(value = "-> new", pure = true)
        public ParticleDisplay build() {
            return new ParticleDisplay(shapes, type, amount, force);
        }
    }
}
