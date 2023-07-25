package net.aethermc;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.instance.InstanceContainer;

@AetherMC(since = "1.0.0")
public final class Aether {

    public static final InstanceContainer instanceContainer = MinecraftServer.getInstanceManager().createInstanceContainer();
    public static final GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
    public static final CommandManager commandManager = MinecraftServer.getCommandManager();

    public Aether() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static PluginManager getPluginManager() {
        return new PluginManager();
    }

    public static InstanceContainer getInstanceContainer() {
        return instanceContainer;
    }

    public static GlobalEventHandler getGlobalEventHandler() {
        return globalEventHandler;
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }
}
