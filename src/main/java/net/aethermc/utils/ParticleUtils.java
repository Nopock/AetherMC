package net.aethermc.utils;

import net.aethermc.particles.ParticleDisplay;
import net.aethermc.particles.ParticleShape;
import net.aethermc.particles.ParticleType;
import net.aethermc.particles.shapes.ParticleSingle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParticleUtils {

    public static ParticleDisplay createSingle(ParticleType type, int amount, boolean force) {
        return new ParticleDisplay(List.of(new ParticleSingle()), type, amount, force);
    }

    public static ParticleDisplay createSingle(ParticleType type, boolean force) {
        return createSingle(type, -1, force);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final List<ParticleShape> shapes = new ArrayList<>();
        private int amount = -1;
        private boolean force = true;
        private ParticleType type = null;

        public Builder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder addShape(ParticleShape shape, ParticleType type, int priority) {
            shapes.add(shape);
            this.type = type;
            return this;
        }

        public Builder addShape(ParticleShape shape, ParticleType type) {
            return addShape(shape, type, 100);
        }

        public Builder setForce(boolean force) {
            this.force = force;
            return this;
        }

        public ParticleDisplay build() {
            return new ParticleDisplay(shapes, type, amount, force);
        }
    }
}
