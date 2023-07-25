package server.test;

import de.articdive.jnoise.generators.noisegen.opensimplex.FastSimplexNoiseGenerator;
import de.articdive.jnoise.pipeline.JNoise;
import net.aethermc.Aether;
import net.aethermc.plugins.PluginLoader;
import net.aethermc.scheduler.AetherScheduler;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.event.player.PlayerSkinInitEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.block.Block;
import server.test.commands.TPSCommand;
import server.test.defaultevents.LoginEvent;
import server.test.defaultevents.SkinInit;

import java.io.File;
import java.util.logging.Logger;

public class Main {

    public static final Logger LOGGER = Logger.getLogger("AetherMC");

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        LOGGER.info("Loading AetherMC...\nVersion: V1.0.0-SNAPSHOT1");
        MinecraftServer server = MinecraftServer.init();
        MinecraftServer.setChunkViewDistance(16);

        LOGGER.info("Loading MojangAuth...");
        MojangAuth.init();
        new LoginEvent();
        new SkinInit();

        LOGGER.info("Loading Commands...");
        Aether.getCommandManager().register(new TPSCommand("tps", "lag"));
        Aether.getInstanceContainer().setChunkSupplier(LightingChunk::new);

        generator();
        server.start("0.0.0.0", 25565);
        LOGGER.info("AetherMC loaded in " + (System.currentTimeMillis() - start) + "ms");
    }

    private static void generator() {
        final JNoise noise = JNoise.newBuilder()
                .fastSimplex(FastSimplexNoiseGenerator.newBuilder().build())
                .scale(0.01)
                .build();

        Aether.getInstanceContainer().setGenerator(unit -> {
            Point start = unit.absoluteStart();
            Point size = unit.size();
            for (int x = 0; x < unit.size().x(); x++) {
                for (int z = 0; z < unit.size().z(); z++) {
                    Point bottom = start.add(x, 0, z);
                    double height = noise.evaluateNoise(bottom.x(), bottom.z()) * 16;
                    unit.modifier().fill(bottom, bottom.add(1, 0, 1).withY(height), Block.GRASS_BLOCK);
                }
            }
        });
    }
}
