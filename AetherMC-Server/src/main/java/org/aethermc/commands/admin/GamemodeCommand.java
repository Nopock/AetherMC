package org.aethermc.commands.admin;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentString;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GamemodeCommand extends Command {

    public GamemodeCommand(@NotNull String name, @Nullable String... aliases) {
        super(name, aliases);

        setDefaultExecutor(((sender, context) -> {
            sender.sendMessage("Usage: /gamemode <gamemode>");
        }));

        ArgumentString gamemodeArgument = ArgumentType.String("gamemode");

        gamemodeArgument.setCallback((sender, exception) -> {
            final String input = exception.getInput();
            sender.sendMessage("This isn't valid! " + input + " is not a valid gamemode.");
        });

        addSyntax(((sender, context) -> {
            if (sender instanceof Player player) {
                final String gamemode = context.get(gamemodeArgument);
                switch (gamemode.toLowerCase()) {
                    case "survival", "s" -> {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage("Your gamemode has been set to survival.");
                    }
                    case "creative", "c" -> {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage("Your gamemode has been set to creative.");
                    }
                    case "spectator", "sp" -> {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage("Your gamemode has been set to spectator.");
                    }
                    case "adventure", "a" -> {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage("Your gamemode has been set to adventure.");
                    }
                }
            }
        }), gamemodeArgument);
    }
}
