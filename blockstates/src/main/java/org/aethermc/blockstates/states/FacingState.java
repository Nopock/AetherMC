package org.aethermc.blockstates.states;

import net.minestom.server.coordinate.Vec;
import net.minestom.server.instance.block.Block;
import org.aethermc.blockstates.BlockState;
import org.aethermc.blockstates.OppositeState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public enum FacingState implements BlockState<FacingState>, OppositeState<FacingState> {
    NORTH(0, 0, 0, "north"),
    EAST(1, 0, 0, "east"),
    SOUTH(1, 0, 1, "south"),
    WEST(0, 0,  1, "west");

    private final int x, y, z;
    private final String propertyValue;

    FacingState(int x, int y, int z, String propertyValue) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.propertyValue = propertyValue;
    }

    @Override
    public FacingState get(Block block) {
        final String facingProperty = block.getProperty("facing");
        if (facingProperty != null) {
            return valueOf(facingProperty.toUpperCase());
        }
        return null;
    }

    @Override
    public Block set(Block block, FacingState value) {
        Map<String, String> properties = new HashMap<>(block.getProperties());
        properties.put("facing", value.propertyValue);
        return block.withProperties(properties);
    }

    @Override
    public @NotNull FacingState getOpposite(@NotNull FacingState value) {
        return switch (value) {
            case NORTH -> SOUTH;
            case EAST -> WEST;
            case SOUTH -> NORTH;
            case WEST -> EAST;
        };
    }

    public static @Nullable FacingState get(@NotNull Vec vec) {
        for (FacingState state : values()) {
            if (state.x == Math.ceil(vec.x()) && state.y == Math.ceil(vec.y()) && state.z == Math.ceil(vec.z()))
                return state;
        }
        return null;
    }
}
