package net.aethermc.world;

import net.aethermc.region.Region;
import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.block.Block;

import java.util.ArrayList;
import java.util.List;

public class WorldProperties {

    private final List<Region> regionsInWorld = new ArrayList<>();
    private final List<Player> playersInWorld = new ArrayList<>();

    public Block getBlockAt(int x, int y, int z) {
        return null;
    }

    public void setBlockAt(int x, int y, int z, Block block) {

    }

    public List<Region> getRegionsInWorld() {
        return regionsInWorld;
    }

    public List<Player> getPlayersInWorld() {
        return playersInWorld;
    }


}
