package net.aethermc;

import net.kyori.adventure.text.Component;
import net.minestom.server.MinecraftServer;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.adventure.bossbar.BossBarManager;
import net.minestom.server.command.CommandManager;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.item.ItemStack;
import net.minestom.server.listener.manager.PacketListenerManager;
import net.minestom.server.monitoring.BenchmarkManager;
import net.minestom.server.network.ConnectionManager;
import net.minestom.server.network.PacketProcessor;
import net.minestom.server.network.socket.Server;
import net.minestom.server.world.Difficulty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

@AetherMC(since = "1.0.0")
public final class Aether {

    public static final InstanceContainer instanceContainer = MinecraftServer.getInstanceManager().createInstanceContainer();
    public static final GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
    public static final CommandManager commandManager = MinecraftServer.getCommandManager();
    public static final ConnectionManager connectionManager = MinecraftServer.getConnectionManager();

    public Aether() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static @Nullable Player getPlayer(@NotNull String string) {
        return getConnectionManager().getPlayer(string);
    }

    public static @Nullable Player getPlayer(@NotNull UUID uuid) {
        return getConnectionManager().getPlayer(uuid);
    }

    public static @NotNull Collection<Player> getAllPlayers() {
        return getConnectionManager().getOnlinePlayers();
    }

    public static void broadcastMessage(String message) {
        Audiences.all().sendMessage(Component.text(ChatColor.color(message)));
        getLogger().log(AetherLogger.Level.INFO, ChatColor.stripColor(message));
    }

    public static int getChunkViewDistance() {
        return MinecraftServer.getChunkViewDistance();
    }

    public static Difficulty getDifficulty() {
        return MinecraftServer.getDifficulty();
    }

    public static int getEntityViewDistance() {
        return MinecraftServer.getEntityViewDistance();
    }

    public static PacketListenerManager getPacketListenerManager() {
        return MinecraftServer.getPacketListenerManager();
    }

    public static PacketProcessor getPacketProcessor() {
        return MinecraftServer.getPacketProcessor();
    }

    public static String getBrandName() {
        return MinecraftServer.getBrandName();
    }

    public static BossBarManager getBossbarManager() {
        return MinecraftServer.getBossBarManager();
    }

    public static BenchmarkManager getBenchmarkManager() {
        return MinecraftServer.getBenchmarkManager();
    }

    public static Server getServer() {
        return MinecraftServer.getServer();
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

    public static ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public static AetherLogger getLogger() {
        return new AetherLogger();
    }
}
