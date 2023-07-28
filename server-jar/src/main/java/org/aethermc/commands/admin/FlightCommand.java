package org.aethermc.commands.admin;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentString;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.number.ArgumentInteger;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlightCommand extends Command {

    public FlightCommand(@NotNull String name) {
        super(name);

        setDefaultExecutor(((sender, context) -> {
            if (sender instanceof Player player) {
                if (player.isFlying()) {
                    player.setFlying(false);
                    player.sendMessage("You are no longer flying.");
                    return;
                }
                player.setFlying(true);
                player.sendMessage("You are now flying.");
            }
        }));
    }
}
