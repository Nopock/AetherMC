package server.test.commands;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.extras.MojangAuth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TPSCommand extends Command {
    public TPSCommand(@NotNull String name, @Nullable String... aliases) {
        super(name, aliases);

        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("TPS: " + MinecraftServer.TICK_PER_SECOND);
        });
    }
}
