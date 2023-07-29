package org.aethermc.blockstates.states;

import net.minestom.server.instance.block.Block;
import org.aethermc.blockstates.BlockState;
import org.aethermc.blockstates.OppositeState;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public enum FacingState implements BlockState<FacingState>, OppositeState<FacingState> {
    NORTH("north"),
    EAST("east"),
    SOUTH("south"),
    WEST("west");

    private final String propertyValue;

    FacingState(String propertyValue) {
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
}
