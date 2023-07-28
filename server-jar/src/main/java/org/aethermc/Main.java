package org.aethermc;

import de.articdive.jnoise.generators.noise_parameters.simplex_variants.Simplex3DVariant;
import de.articdive.jnoise.generators.noisegen.opensimplex.FastSimplexNoiseGenerator;
import de.articdive.jnoise.pipeline.JNoise;
import net.aethermc.Aether;
import net.aethermc.AetherLogger;
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

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        MinecraftServer server = MinecraftServer.init();
        AetherLogger logger = Aether.getLogger();

        logger.log(AetherLogger.Level.INFO, "Loading AetherMC...");
        logger.log(AetherLogger.Level.INFO, "Version: V1.0.0-snapshot.3");
        JarLoader.checkFiles();

        Aether.getInstanceContainer().setChunkSupplier(LightingChunk::new);

        logger.log(AetherLogger.Level.INFO, "Loading MojangAuth...");
        MojangAuth.init();
        new LoginEvent();

        logger.log(AetherLogger.Level.INFO, "Loading Commands...");
        new CommandHandler().registerCommands(Aether.getCommandManager());

        logger.log(AetherLogger.Level.INFO, "Loading Plugins...");

        new WorldGeneration();
        server.start("0.0.0.0", 25565);

        Aether.getPluginManager().registerAllPlugins().thenAccept(plugins -> {
            logger.log(AetherLogger.Level.INFO, "AetherMC has been loaded with " + plugins + " plugins in " + (System.currentTimeMillis() - start) + "ms");
        });
    }
}