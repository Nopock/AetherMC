package net.aethermc.blockstates;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.Material;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class is experimental and isn't finished
 */
@ApiStatus.Experimental
public class StateHandler {

    /**
     * Main interface for all state handlers
     *
     * @param <T>
     */
    interface State<T> {

        @Nullable T getState();

        @NotNull Block setState(@NotNull Block block, @NotNull Pos pos);
    }

    /**
     * All openable blocks, such as doors, trapdoors, and fence gates
     */
    public enum Openable implements State<Openable> {
        OAK_TRAPDOOR(Material.OAK_TRAPDOOR),
        SPRUCE_TRAPDOOR(Material.SPRUCE_TRAPDOOR),
        BIRCH_TRAPDOOR(Material.BIRCH_TRAPDOOR),
        JUNGLE_TRAPDOOR(Material.JUNGLE_TRAPDOOR),
        ACACIA_TRAPDOOR(Material.ACACIA_TRAPDOOR),
        DARK_OAK_TRAPDOOR(Material.DARK_OAK_TRAPDOOR),
        CRIMSON_TRAPDOOR(Material.CRIMSON_TRAPDOOR),
        WARPED_TRAPDOOR(Material.WARPED_TRAPDOOR),
        IRON_TRAPDOOR(Material.IRON_TRAPDOOR),
        OAK_DOOR(Material.OAK_DOOR),
        SPRUCE_DOOR(Material.SPRUCE_DOOR),
        BIRCH_DOOR(Material.BIRCH_DOOR),
        JUNGLE_DOOR(Material.JUNGLE_DOOR),
        ACACIA_DOOR(Material.ACACIA_DOOR),
        DARK_OAK_DOOR(Material.DARK_OAK_DOOR),
        CRIMSON_DOOR(Material.CRIMSON_DOOR),
        WARPED_DOOR(Material.WARPED_DOOR),
        IRON_DOOR(Material.IRON_DOOR),
        OAK_FENCE_GATE(Material.OAK_FENCE_GATE),
        SPRUCE_FENCE_GATE(Material.SPRUCE_FENCE_GATE),
        BIRCH_FENCE_GATE(Material.BIRCH_FENCE_GATE),
        JUNGLE_FENCE_GATE(Material.JUNGLE_FENCE_GATE),
        ACACIA_FENCE_GATE(Material.ACACIA_FENCE_GATE),
        DARK_OAK_FENCE_GATE(Material.DARK_OAK_FENCE_GATE),
        CRIMSON_FENCE_GATE(Material.CRIMSON_FENCE_GATE),
        WARPED_FENCE_GATE(Material.WARPED_FENCE_GATE);


        private final Material material;

        Openable(Material material) {
            this.material = material;
        }

        public Material getMaterial() {
            return material;
        }

        @Override
        public @Nullable Openable getState() {
            for (Openable openable : values()) {
                if (openable.getMaterial() == material) {
                    return openable;
                }
            }
            return null;
        }

        @Override
        public @NotNull Block setState(@NotNull Block block, @NotNull Pos pos) {
            final String property = block.getProperty("open");
            return block.withProperty("open", property.equals("true") ? "false" : "true");
        }
    }
}
