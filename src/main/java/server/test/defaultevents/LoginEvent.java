package server.test.defaultevents;

import net.aethermc.Aether;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.player.PlayerLoginEvent;

public class LoginEvent {

    public LoginEvent() {
        Aether.getGlobalEventHandler().addListener(PlayerLoginEvent.class, event -> {
            event.setSpawningInstance(Aether.getInstanceContainer());
            event.getPlayer().setRespawnPoint(new Pos(0, 42, 0));

            // TODO: Save player location when they leave and teleport them there when they join
        });
    }
}
