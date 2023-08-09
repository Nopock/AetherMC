package org.aethermc;

import net.aethermc.Aether;
import net.aethermc.AetherLogger;
import net.aethermc.events.PlayerDiggingEvent;
import net.aethermc.particles.ParticleDisplay;
import net.aethermc.plugins.loaders.AetherPluginLoader;
import net.aethermc.plugins.loaders.PluginLoader;
import net.aethermc.utils.NumberUtils;
import net.aethermc.utils.ParticleUtils;
import net.aethermc.particles.shapes.ParticleCircle;
import net.aethermc.particles.types.ParticleTransition;
import net.aethermc.utils.PluginUtils;
import net.minestom.server.MinecraftServer;
import net.minestom.server.color.Color;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.extras.PlacementRules;
import net.minestom.server.instance.LightingChunk;
import org.aethermc.events.LoginEvent;
import org.aethermc.events.custom.EventListener;
public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        MinecraftServer server = MinecraftServer.init();
        AetherLogger logger = Aether.getLogger();
        logger.info(String.format("Loading %s", new Aether().getClass().getName()));

        logger.info("Checking for updates...");
        // TODO: Check for updates here

        logger.info("-------------------------");
        logger.info("Loading AetherMC...");
        logger.info("- Version: V1.0.0-snapshot.4");
        logger.info("- Pre-Release: true");
        logger.info("-------------------------");

        logger.info("Checking Files");
        JarLoader.checkFiles();

        logger.info("Loading AetherMC-settings.yml");
        // load settings
        logger.success("Configured Settings (0.00s)");

        logger.info(String.format("Starting Minecraft Server on version %s", MinecraftServer.VERSION_NAME));
        PlacementRules.init();
        Aether.getInstanceContainer().setChunkSupplier(LightingChunk::new);

        logger.info("Loading MojangAuth...");
        MojangAuth.init();
        new LoginEvent();

        logger.info("Loading Event Handlers...");
        new EventListener();

        logger.info("Loading Commands...");
        new CommandHandler().registerCommands(Aether.getCommandManager());

        logger.info("Loading Plugins...");
        PluginUtils.loadPlugins().thenAccept(plugins -> {
            logger.success(String.format("AetherMC has been loaded with %s plugins in %.2fs", plugins, (System.currentTimeMillis() - start) / 1000.0));
        });

        new WorldGeneration();
        server.start("0.0.0.0", 25565);
    }
}