package org.aethermc.events;

import net.aethermc.Aether;
import net.aethermc.AetherLogger;
import net.aethermc.data.player.DataSerializer;
import net.aethermc.data.player.PlayerData;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LoginEvent {

    public LoginEvent() {
        GlobalEventHandler eventHandler = Aether.getGlobalEventHandler();
        eventHandler.addListener(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            long start = System.currentTimeMillis();

            event.setSpawningInstance(Aether.getInstanceContainer());

            // TODO: Save player location when they leave and teleport them there when they join
            if (DataSerializer.hasData(player)) {
                Aether.broadcastMessage(String.format("Loading %s PlayerData!", player.getUsername()));
                PlayerData data = (PlayerData) DataSerializer.getData(player);
                spawn(player, data);
            } else {
                Aether.broadcastMessage(String.format("Creating %s PlayerData!", player.getUsername()));
                PlayerData newData = new PlayerData(player.getUuid(), player.getPosition(), (player.getPermissionLevel() == 4));
                spawn(player, newData);
            }

            player.setRespawnPoint(new Pos(0, 42, 0));
            Aether.getLogger().log(AetherLogger.Level.INFO, String.format("%s has connected with uuid %s (%.2fs)", player.getUsername(), player.getUuid(), (System.currentTimeMillis() - start) / 1000.0));
            Aether.broadcastMessage("&e" + player.getUsername() + " has joined the server!");
        });

        eventHandler.addListener(PlayerDisconnectEvent.class, event -> {
            final Player player = event.getPlayer();
            long start = System.currentTimeMillis();
            DataSerializer.saveData(player, DataSerializer.getData(player));
            Aether.getLogger().log(AetherLogger.Level.INFO, String.format("%s has disconnected with uuid %s (%.2fs)", player.getUsername(), player.getUuid(), (System.currentTimeMillis() - start) / 1000.0));
            Aether.broadcastMessage("&e" + player.getUsername() + " has left the server!");
            DataSerializer.removeData(player.getUuid().toString());
        });
    }

    private void spawn(@NotNull Player player, PlayerData data) {
        Pos respawnPos;
        if (data == null) {
            respawnPos = new Pos(0, 42, 0);
        } else {
            respawnPos = data.getLastPosition();
        }
        player.setRespawnPoint(respawnPos);
        DataSerializer.putData(player.getUuid().toString(), data);
    }
}
