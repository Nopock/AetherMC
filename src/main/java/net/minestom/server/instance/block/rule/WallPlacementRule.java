package net.minestom.server.instance.block.rule;

import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class WallPlacementRule extends BlockPlacementRule {

    public WallPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public @NotNull Block blockUpdate(@NotNull UpdateState updateState) {
        final Point blockPosition = updateState.blockPosition();
        final Block.Getter blockGetter = updateState.instance();

        final int x = blockPosition.blockX();
        final int y = blockPosition.blockY();
        final int z = blockPosition.blockZ();

        String east = "none";
        String north = "none";
        String south = "none";
        String up = "true";
        String waterlogged = "false";
        String west = "none";

        if (isBlock(blockGetter, x + 1, y, z)) {
            east = "low";
        }

        if (isBlock(blockGetter, x - 1, y, z)) {
            west = "low";
        }

        if (isBlock(blockGetter, x, y, z + 1)) {
            south = "low";
        }

        if (isBlock(blockGetter, x, y, z - 1)) {
            north = "low";
        }

        return block.withProperties(Map.of(
                "east", east,
                "north", north,
                "south", south,
                "west", west,
                "up", up,
                "waterlogged", waterlogged));
    }

    @Override
    public @Nullable Block blockPlace(@NotNull PlacementState placementState) {
        return placementState.block();
    }

    private boolean isBlock(Block.Getter instance, int x, int y, int z) {
        return instance.getBlock(x, y, z).isSolid();
    }
}
