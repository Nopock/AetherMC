package org.aethermc.commands;

import net.minestom.server.command.builder.Command;
import org.jetbrains.annotations.NotNull;

public class ServerCommand extends Command {
    public ServerCommand(@NotNull String name) {
        super(name);

        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("AetherMC-Server: AetherMC-V1.0.0-snapshot.3 (1.20.1)");
            sender.sendMessage("Version: V1.0.0-snapshot.3");
        });
    }
}
