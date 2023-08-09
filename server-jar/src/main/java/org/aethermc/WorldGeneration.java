package org.aethermc;

import de.articdive.jnoise.generators.noise_parameters.simplex_variants.Simplex3DVariant;
import de.articdive.jnoise.generators.noisegen.opensimplex.FastSimplexNoiseGenerator;
import de.articdive.jnoise.pipeline.JNoise;
import net.aethermc.Aether;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;

public class WorldGeneration {

    public WorldGeneration() {
        final JNoise noise = JNoise.newBuilder()
                .fastSimplex(FastSimplexNoiseGenerator.newBuilder().setVariant3D(Simplex3DVariant.IMPROVE_XY).build())
                .scale(0.005)
                .build();

        Aether.getInstanceContainer().setGenerator(unit -> {
            Point start = unit.absoluteStart();
            for (int x = 0; x < unit.size().x(); x++) {
                for (int z = 0; z < unit.size().z(); z++) {
                    Point bottom = start.add(x, 0, z);
                    double height = noise.evaluateNoise(bottom.x(), bottom.z()) * 64;
                    unit.modifier().fill(bottom, bottom.add(1, 0, 1).withY(height), Block.GRASS_BLOCK);
                }
            }
        });
    }
}
