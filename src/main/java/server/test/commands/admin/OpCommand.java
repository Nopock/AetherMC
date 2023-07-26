package server.test.commands.admin;

import net.aethermc.Aether;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentString;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OpCommand extends Command {

    public OpCommand(@NotNull String name, @Nullable String... aliases) {
        super(name, aliases);

        setDefaultExecutor(((sender, context) -> {
            sender.sendMessage("Usage: /op <player>");
        }));

        ArgumentString argumentString = ArgumentType.String("player");

        addSyntax(((sender, context) -> {
            if (sender instanceof Player senderPlayer) {
                if (!senderPlayer.hasPermission("aethermc.op")) {
                    senderPlayer.sendMessage("You do not have permission to use this command.");
                } else {
                    final Player player = Aether.getPlayer(context.get(argumentString));
                    player.setPermissionLevel(4);
                    sender.sendMessage("You have successfully opped " + player);
                }
            }
        }), argumentString);
    }
}
