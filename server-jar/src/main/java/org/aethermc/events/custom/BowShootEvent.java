package org.aethermc.events.custom;

import net.minestom.server.entity.Player;
import net.minestom.server.event.Event;
import net.minestom.server.item.ItemStack;

public class BowShootEvent implements Event {

    private final Player player;
    private final ItemStack heldItem;
    private final double power;
    private final double chargedFor;

    public BowShootEvent(Player player, ItemStack heldItem, double power, double chargedFor) {
        this.player = player;
        this.heldItem = heldItem;
        this.power = power;
        this.chargedFor = chargedFor;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getHeldItem() {
        return heldItem;
    }

    public double getPower() {
        return power;
    }

    public double getChargedFor() {
        return chargedFor;
    }
}
