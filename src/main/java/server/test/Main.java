package server.test;

import net.minestom.server.MinecraftServer;

public class Main {

    public static void main(String[] args) {
        MinecraftServer server = MinecraftServer.init();

        server.start("0.0.0.0", 25565);
    }
}
