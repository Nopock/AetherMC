package org.aethermc.events.custom;

import net.aethermc.Aether;
import net.aethermc.AetherLogger;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.EntityProjectile;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.ItemUpdateStateEvent;
import net.minestom.server.event.player.PlayerBlockInteractEvent;
import net.minestom.server.event.player.PlayerBlockPlaceEvent;
import net.minestom.server.event.player.PlayerItemAnimationEvent;
import net.minestom.server.instance.block.Block;
import net.minestom.server.tag.Tag;
import net.minestom.server.utils.MathUtils;
import org.aethermc.blockstates.states.FacingState;

import java.util.Random;

public class EventListener {

    private static final Tag<Long> CHARGE_SINCE_TAG = Tag.Long("bow_charge_since").defaultValue(Long.MAX_VALUE);

    public EventListener() {
        GlobalEventHandler handler = MinecraftServer.getGlobalEventHandler();

        // bows
        handler.addListener(PlayerItemAnimationEvent.class, event -> {
            if (event.getItemAnimationType() == PlayerItemAnimationEvent.ItemAnimationType.BOW) {
                event.getPlayer().setTag(CHARGE_SINCE_TAG, System.currentTimeMillis());
            }
        }).addListener(ItemUpdateStateEvent.class, event -> {
            final Player player = event.getPlayer();
            final long chargedSince = player.getTag(CHARGE_SINCE_TAG);
            if (chargedSince == Long.MAX_VALUE) return;

            final double chargedFor = (System.currentTimeMillis() - chargedSince) / 1000D;
            final double power = MathUtils.clamp((chargedFor * chargedFor + 2 * chargedFor) / 2D, 0, 1);

            if (power > 0.2) {
                final EntityProjectile projectile = new EntityProjectile(player, EntityType.ARROW);
                final Pos playerPosition = player.getPosition();
                final Pos position = playerPosition.add(0, player.getEyeHeight(), 0);

                projectile.setInstance(player.getInstance(), position);

                Vec direction = projectile.getPosition().direction();
                projectile.shoot(position.add(direction).sub(0, 0.2, 0), power * 3, 1.0);
            }
        });

        // directional blocks
//        handler.addListener(PlayerBlockPlaceEvent.class, event -> {
//            final Player player = event.getPlayer();
//            final Block block = event.getBlock();
//
//            if (block.getProperty("facing") != null) {
//                FacingState playerState = FacingState.get(player.getPosition().direction());
//                if (playerState == null) return;
//
//                event.setBlock(block.withProperty("facing", playerState.name().toLowerCase()));
//            }
//        });
    }
}
