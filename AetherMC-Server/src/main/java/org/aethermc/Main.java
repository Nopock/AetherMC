package org.aethermc;

import de.articdive.jnoise.generators.noise_parameters.simplex_variants.Simplex3DVariant;
import de.articdive.jnoise.generators.noisegen.opensimplex.FastSimplexNoiseGenerator;
import de.articdive.jnoise.pipeline.JNoise;
import net.aethermc.Aether;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.coordinate.Point;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.block.Block;
import org.aethermc.commands.TPSCommand;
import org.aethermc.commands.admin.FlightCommand;
import org.aethermc.commands.admin.GamemodeCommand;
import org.aethermc.commands.admin.OpCommand;
import org.aethermc.defaultevents.LoginEvent;

import java.util.logging.Logger;

public class Main {

    public static final Logger LOGGER = Logger.getLogger("AetherMC");

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        LOGGER.info("Loading AetherMC...\nVersion: V1.0.0-SNAPSHOT1");
        MinecraftServer server = MinecraftServer.init();
        MinecraftServer.setChunkViewDistance(16);

        Aether.getInstanceContainer().setChunkSupplier(LightingChunk::new);

        LOGGER.info("Loading MojangAuth...");
        MojangAuth.init();
        new LoginEvent();

        LOGGER.info("Loading Commands...");
        CommandManager manager = Aether.getCommandManager();

        manager.register(new TPSCommand("tps", "lag"));
        manager.register(new GamemodeCommand("gamemode", "gm"));
        manager.register(new OpCommand("operator", "op"));
        manager.register(new FlightCommand("fly"));

        generator();
        server.start("0.0.0.0", 25565);
        LOGGER.info("AetherMC loaded in " + (System.currentTimeMillis() - start) + "ms");
    }

    private static void generator() {
        final JNoise noise = JNoise.newBuilder()
                .fastSimplex(FastSimplexNoiseGenerator.newBuilder().setVariant3D(Simplex3DVariant.IMPROVE_XY).build())
                .scale(0.005)
                .build();

        Aether.getInstanceContainer().setGenerator(unit -> {
            Point start = unit.absoluteStart();
            Point size = unit.size();
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