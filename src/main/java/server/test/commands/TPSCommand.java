package server.test.commands;

import net.aethermc.ChatColor;
import net.minestom.server.command.builder.Command;
import net.minestom.server.utils.time.Tick;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TPSCommand extends Command {
    public TPSCommand(@NotNull String name, @Nullable String... aliases) {
        super(name, aliases);

        setDefaultExecutor((sender, context) -> {
            long ramUsage = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576;
            long ramTotal = Runtime.getRuntime().totalMemory() / 1048576;

            sender.sendMessage(ChatColor.color("&7TPS: &a" + Tick.SERVER_TICKS.getTicksPerSecond()));
            sender.sendMessage(ChatColor.color("&7Ram Usage: &a" + ramUsage + "MB/" + ramTotal + "MB &8(&c" + (ramUsage * 100 / ramTotal) + "%&8)"));
        });
    }
}
