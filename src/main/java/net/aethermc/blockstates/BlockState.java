package net.aethermc.blockstates;

import net.aethermc.blockstates.states.State;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;

import java.util.HashMap;

public class BlockState {

    private final HashMap<String, String> blockProperties = new HashMap<>();

    private Block block;
    private BlockHandler blockHandler;
    private NBTCompound nbtCompound;

    protected BlockState(Block block) {
        this.block = block;
        this.blockHandler = block.handler();
        this.nbtCompound = block.nbt();

        blockProperties.putAll(block.properties());
    }

    private void private_set(String key, String value) {
        if (!blockProperties.containsKey(key)) return;

        blockProperties.remove(key);
        blockProperties.put(key, value);
    }

    public void set(State<?> key, State<?> value) {
        private_set(key.getKey(), value.getValue());
    }

    public <T extends Comparable<T>> void set(State<T> key, T value) {
        private_set(key.getKey(), String.valueOf(value));
    }

    public void set(State<?> state) {
        private_set(state.getKey(), state.getValue());
    }

    public Block block() {
        if (blockHandler != null)
            block = block.withHandler(blockHandler);

        if (nbtCompound != null)
            block = block.withNbt(nbtCompound);

        return block.withProperties(blockProperties);
    }
}
