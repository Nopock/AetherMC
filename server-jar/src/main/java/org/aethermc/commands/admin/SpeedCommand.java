package org.aethermc.commands.admin;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.number.ArgumentInteger;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpeedCommand extends Command {

    public SpeedCommand(@NotNull String name) {
        super(name);

        setDefaultExecutor(((sender, context) -> {
            sender.sendMessage("Usage: /speed <integer>");
        }));

        ArgumentInteger integer = ArgumentType.Integer("speed");

        addSyntax(((sender, context) -> {
            if (sender instanceof Player player) {
                int speed = context.get(integer);
                player.setFlyingSpeed(speed);
                player.sendMessage("You've set your flight speed to " + speed);
            }
        }), integer);
    }
}
