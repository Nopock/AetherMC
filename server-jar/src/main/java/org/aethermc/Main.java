package org.aethermc;

import net.aethermc.Aether;
import net.aethermc.AetherLogger;
import net.aethermc.ChatColor;
import net.aethermc.events.PlayerDiggingEvent;
import net.aethermc.particles.ParticleDisplay;
import net.aethermc.utils.ParticleUtils;
import net.aethermc.particles.shapes.ParticleCircle;
import net.aethermc.particles.types.ParticleTransition;
import net.minestom.server.MinecraftServer;
import net.minestom.server.color.Color;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
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
            logger.log(AetherLogger.Level.INFO, String.format("AetherMC has been loaded with %s plugins in %.2fs", plugins, (System.currentTimeMillis() - start) / 1000.0));
        });

        Aether.getGlobalEventHandler().addListener(PlayerBlockBreakEvent.class, event -> {
            Pos pos = event.getPlayer().getPosition();
            ParticleDisplay display = ParticleUtils.builder()
                    .addShape(new ParticleCircle(5, 2),
                            new ParticleTransition(
                                    new Color(255, 0, 0),
                                    new Color(0, 255, 0)
                            ))
                    .setForce(false)
                    .build();

            display.show(pos);
//            for (int i = 0; i < 25; i++) {
//                display.show(pos);
//                Aether.broadcastMessage(String.valueOf(i));
//                ((ParticleCircle) display.getShape(0)).setSize(i);
//            }
        });

        Aether.getGlobalEventHandler().addListener(PlayerDiggingEvent.class, event -> {
            final Player player = event.getPlayer();
            switch (event.getStatus()) {
                case STARTED_DIGGING -> player.sendMessage("Started digging");
                case CANCELLED_DIGGING -> player.sendMessage("Cancelled digging");
                case FINISHED_DIGGING -> player.sendMessage("Finished digging");
            }
        });
    }
}