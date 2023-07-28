package org.aethermc;

import net.minestom.server.command.CommandManager;
import net.minestom.server.command.builder.Command;
import org.aethermc.commands.PluginsCommand;
import org.aethermc.commands.ServerCommand;
import org.aethermc.commands.TPSCommand;
import org.aethermc.commands.admin.FlightCommand;
import org.aethermc.commands.admin.GamemodeCommand;
import org.aethermc.commands.admin.OpCommand;
import org.aethermc.commands.admin.SpeedCommand;

import java.util.jar.JarFile;

public class CommandHandler {

    public void registerCommands(CommandManager manager) {
        // TODO: In the future use reflection to register all commands in a package
        manager.register(new TPSCommand("tps", "lag"));
        manager.register(new GamemodeCommand("gamemode", "gm"));
        manager.register(new OpCommand("operator", "op"));
        manager.register(new FlightCommand("fly"));
        manager.register(new SpeedCommand("speed"));
        manager.register(new PluginsCommand("plugins", "pl"));
        manager.register(new ServerCommand("server"));
    }

    public boolean isCommand(Class<?> clazz) {
        return clazz.isAssignableFrom(Command.class);
    }
}
