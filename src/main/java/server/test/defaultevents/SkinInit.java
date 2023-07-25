package server.test.defaultevents;

import net.aethermc.Aether;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.event.player.PlayerSkinInitEvent;

public class SkinInit {

    public SkinInit() {
        Aether.getGlobalEventHandler().addListener(PlayerSkinInitEvent.class, event -> {
            event.setSkin(PlayerSkin.fromUsername(event.getPlayer().getUsername().toString()));
        });
    }
}
