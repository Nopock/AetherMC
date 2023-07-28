package org.aethermc.defaultevents;

import net.aethermc.Aether;
import net.aethermc.AetherLogger;
import net.aethermc.ChatColor;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerLoginEvent;

public class LoginEvent {

    public LoginEvent() {
        Aether.getGlobalEventHandler().addListener(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();

            event.setSpawningInstance(Aether.getInstanceContainer());
            player.setRespawnPoint(new Pos(0, 42, 0));
            Aether.getLogger().log(AetherLogger.Level.INFO, player.getUsername() + " has connected (" + player.getUuid() + ")");
            Aether.broadcastMessage("&e" + player.getUsername() + " has joined the server!");

            // TODO: Save player location when they leave and teleport them there when they join
        });
    }
}
