package net.aethermc.blockstates.states;

import net.aethermc.AetherMC;
import net.aethermc.blockstates.BlockState;
import net.minestom.server.instance.block.Block;

@AetherMC(since = "1.0.0")
public class WaterLoggedState extends BlockState {

    protected WaterLoggedState(Block block) {
        super(block);
    }
}
