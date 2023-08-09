package net.minestom.server.instance.block.rule;

import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jglrxavpok.hephaistos.mca.BlockState;

public class AxisPlacementRule extends BlockPlacementRule {


    public AxisPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public @Nullable Block blockPlace(@NotNull PlacementState placementState) {
        final BlockFace face = placementState.blockFace();

        String axis = "y";
        if (face == BlockFace.WEST || face == BlockFace.EAST) {
            axis = "x";
        } else if (face == BlockFace.NORTH || face == BlockFace.SOUTH) {
            axis = "z";
        }

        return block.withProperty("axis", axis);
    }
}
