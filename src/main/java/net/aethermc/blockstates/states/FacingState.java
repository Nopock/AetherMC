package net.aethermc.blockstates.states;

import net.aethermc.AetherMC;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.utils.Direction;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

@AetherMC(since = "1.0.0")
public enum FacingState implements State<FacingState> {
    SELF(0, 0, 0, null, 0),
    WEST(-1, 0, 0, BlockFace.WEST, 4),
    EAST(1, 0, 0, BlockFace.EAST, 5),
    NORTH(0, 0, -1, BlockFace.NORTH, 2),
    SOUTH(0, 0, 1, BlockFace.SOUTH, 3),
    UP(0, 1, 0, BlockFace.TOP, 1),
    DOWN(0, -1, 0, BlockFace.BOTTOM, 0);

    public static final FacingState[] axis = {FacingState.NORTH, FacingState.EAST, FacingState.SOUTH, FacingState.WEST};


    private final int x, y, z;
    private final BlockFace blockFace;
    private final int packetIndex;

    FacingState(int x, int y, int z, BlockFace blockFace, int packetIndex) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.blockFace = blockFace;
        this.packetIndex = packetIndex;
    }

    public BlockFace getMinestomBlockFace() {
        return blockFace;
    }

    public int getPacketIndex() {
        return packetIndex;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Pos relative(Pos from) {
        return blockFace == null ? from : from.relative(getMinestomBlockFace());
    }

    public AxisState getAxis() {
        return AxisState.of(this);
    }

    public static FacingState fromYaw(float yaw) {
        return axis[Math.round(yaw / 90F) & 3].opposite();
    }

    public FacingState opposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case EAST -> WEST;
            case WEST -> EAST;
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            default -> SELF;
        };
    }

    @Override
    public @NotNull String getKey() {
        return "facing";
    }

    @Override
    public @NotNull String getValue() {
        return name().trim().toLowerCase();
    }

    @Override
    public FacingState parseValue(String input) {
        if (input == null || input.isEmpty())
            return SELF;
        return Stream.of(values()).filter(shape -> shape.name().equalsIgnoreCase(input.trim())).findFirst().orElse(SELF);
    }

    public static FacingState parse(BlockFace input) {
        return Stream.of(values()).filter(facing -> facing.blockFace == input).findFirst().orElse(SELF);
    }

    public static FacingState parse(Direction input) {
        return Stream.of(values()).filter(facing -> facing.name().trim().toLowerCase().equalsIgnoreCase(input.name().trim())).findFirst().orElse(SELF);
    }
}
