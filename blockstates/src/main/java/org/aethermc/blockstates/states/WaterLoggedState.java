package org.aethermc.blockstates.states;

import net.minestom.server.instance.block.Block;
import org.aethermc.blockstates.BlockState;

import java.util.Map;

public enum WaterLoggedState implements BlockState<WaterLoggedState> {
    TRUE("true"),
    FALSE("false");

    private final String propertyValue;

    WaterLoggedState(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    @Override
    public WaterLoggedState get(Block block) {
        final String waterLoggedProperty = block.getProperty("waterlogged");
        if (waterLoggedProperty != null) {
            return valueOf(propertyValue.toUpperCase());
        }
        return null;
    }

    @Override
    public Block set(Block block, WaterLoggedState value) {
        final Map<String, String> properties = block.getProperties();
        properties.put("waterlogged", value.propertyValue);
        return block.withProperties(properties);
    }
}
