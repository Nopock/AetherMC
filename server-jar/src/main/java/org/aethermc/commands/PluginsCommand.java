package org.aethermc.commands;

import net.aethermc.ChatColor;
import net.minestom.server.command.builder.Command;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PluginsCommand extends Command {

    public PluginsCommand(@NotNull String name, @Nullable String... aliases) {
        super(name, aliases);

        setDefaultExecutor((sender, context) -> {
            List<String> pluginList = List.of("AetherMC-Server", "AetherMC-Client");
            final String plugins = join(pluginList, "&f, &a");
            sender.sendMessage(ChatColor.color("&fPlugins (&c3&f):"));
            sender.sendMessage(ChatColor.color("&8- &fEnabled: &a" + plugins));
            sender.sendMessage(ChatColor.color("&8- &fDisabled: &cAetherMC"));
        });
    }

    public static String join(List<String> list, String join) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String string = list.get(i);
            if (i == list.size() - 1) {
                builder.append(string);
            } else {
                builder.append(string).append(join);
            }
        }
        return builder.toString();
    }
}
