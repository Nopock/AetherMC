package net.aethermc.blockstates.states;

import org.jetbrains.annotations.NotNull;

public enum AxisState implements State<AxisState> {
    X, Y, Z;

    public static AxisState of(FacingState blockFace) {
        return switch (blockFace) {
            case EAST, WEST -> X;
            case NORTH, SOUTH -> Z;
            default -> Y;
        };
    }

    public boolean isHorizontal() {
        return this == X || this == Z;
    }

    @Override
    public @NotNull String getKey() {
        return "axis";
    }

    @Override
    public @NotNull String getValue() {
        return name().trim().toLowerCase();
    }

    @Override
    public @NotNull AxisState parseValue(@NotNull String value) {
        if (value.equalsIgnoreCase("x"))
            return X;
        else if (value.equalsIgnoreCase("y"))
            return Y;
        else return Z;
    }
}
