package org.aethermc.blockstates;

import net.minestom.server.instance.block.Block;
import net.minestom.server.registry.Registry;

public interface BlockState<T> {

    T get(Block block);

    Block set(Block block, T value);
}
