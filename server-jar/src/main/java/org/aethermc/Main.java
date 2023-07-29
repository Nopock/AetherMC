package org.aethermc;

import net.aethermc.Aether;
import net.aethermc.AetherLogger;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.EventDispatcher;
import net.minestom.server.event.EventNode;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.LightingChunk;
import org.aethermc.events.LoginEvent;
import org.aethermc.events.custom.EventListener;

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

        logger.log(AetherLogger.Level.INFO, "Loading Event Handlers...");
        new EventListener();

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