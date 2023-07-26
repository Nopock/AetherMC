package server.test.commands.admin;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.arguments.number.ArgumentInteger;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlightCommand extends Command {

    public FlightCommand(@NotNull String name) {
        super(name);

        setDefaultExecutor(((sender, context) -> {
            sender.sendMessage("Usage: /flight <player>");
        }));

        ArgumentInteger integer = ArgumentType.Integer("speed");

        addSyntax(((sender, context) -> {
            if (sender instanceof Player player) {
                player.setFlying(true);
                player.setFlyingSpeed(context.get(integer));
                player.sendMessage("You are now flying.");
            }
        }), integer);
    }
}
