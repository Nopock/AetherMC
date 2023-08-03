package org.aethermc.events;

import net.aethermc.Aether;
import net.aethermc.AetherLogger;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.event.player.PlayerLoginEvent;

public class LoginEvent {

    public LoginEvent() {
        GlobalEventHandler eventHandler = Aether.getGlobalEventHandler();
        eventHandler.addListener(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            long start = System.currentTimeMillis();

            event.setSpawningInstance(Aether.getInstanceContainer());

            // TODO: Save player location when they leave and teleport them there when they join

            player.setRespawnPoint(new Pos(0, 42, 0));
            Aether.getLogger().log(AetherLogger.Level.INFO, String.format("%s has connected with uuid %s (%.2fs)", player.getUsername(), player.getUuid(), (System.currentTimeMillis() - start) / 1000.0));
            Aether.broadcastMessage("&e" + player.getUsername() + " has joined the server!");
        });

        eventHandler.addListener(PlayerDisconnectEvent.class, event -> {
            final Player player = event.getPlayer();
            long start = System.currentTimeMillis();
            Aether.getLogger().log(AetherLogger.Level.INFO, String.format("%s has disconnected with uuid %s (%.2fs)", player.getUsername(), player.getUuid(), (System.currentTimeMillis() - start) / 1000.0));
            Aether.broadcastMessage("&e" + player.getUsername() + " has left the server!");
        });
    }
}
