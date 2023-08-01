package net.aethermc.data.player;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import net.aethermc.Aether;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public final class DataSerializer {

    public static final Cache<String, Data> dataCache = Caffeine.newBuilder()
            .build();

    private static final File PLAYER_DATA = new File("./cache/player_data.bin");

    static {
        if (!PLAYER_DATA.exists()) {
            try {
                PLAYER_DATA.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void serialize(@NotNull Player player, @NotNull Data data) {
        CompletableFuture.runAsync(() -> {
            Map<String, Data> map = new HashMap<>();
            map.put(player.getUuid().toString(), data);

            try (FileOutputStream stream = new FileOutputStream(PLAYER_DATA)) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
                objectOutputStream.writeObject(map);

                objectOutputStream.flush();
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static Map<String, Data> deserialize() {
        Map<String, Data> map = new HashMap<>();

        try (FileInputStream stream = new FileInputStream(PLAYER_DATA)) {
            Aether.broadcastMessage(stream.toString());
            ObjectInputStream objectInputStream = new ObjectInputStream(stream);

            try {
                map = (Map<String, Data>) objectInputStream.readObject();
            } catch (EOFException e) {

            }
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException ignored) {

        }
        return map;
    }

    public static Data getData(@NotNull Player player) {
        var map = deserialize();
        Objects.requireNonNull(map, "Map cannot be null");

        return map.get(player.getUuid().toString());
    }

    public static void saveData(@NotNull Player player, @NotNull Data data) {
        serialize(player, data);
    }

    public static boolean hasData(@NotNull Player player) {
        var map = deserialize();
        Objects.requireNonNull(map, "Map cannot be null");

        return map.containsKey(player.getUuid().toString());
    }

    public static void putData(String uuid, Data data) {
        dataCache.put(uuid, data);
    }

    public static void removeData(String uuid) {
        dataCache.invalidate(uuid);
    }

    public static boolean hasData(String uuid) {
        return dataCache.getIfPresent(uuid) != null;
    }

    public static Data getData(String uuid) {
        return dataCache.getIfPresent(uuid);
    }
}
