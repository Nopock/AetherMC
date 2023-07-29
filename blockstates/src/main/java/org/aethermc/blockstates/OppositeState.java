package org.aethermc.blockstates;

import org.jetbrains.annotations.NotNull;

public interface OppositeState<T> {

    @NotNull T getOpposite(@NotNull T value);
}
