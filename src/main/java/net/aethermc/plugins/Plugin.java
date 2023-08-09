package net.aethermc.plugins;

import net.aethermc.AetherMC;

import java.util.List;

@AetherMC(since = "1.0.0-snapshot.4")
public class Plugin {

    private boolean isAsyncEnable = false;
    private boolean isAsyncDisable = false;

    public void onEnable() {}

    public void onDisable() {}

    public void onLoad() {}

    public void onReload() {}
    public boolean isAsyncEnable() {
        return isAsyncEnable;
    }

    public boolean isAsyncDisable() {
        return isAsyncDisable;
    }

    protected void setAsyncEnable(boolean asyncEnable) {
        isAsyncEnable = asyncEnable;
    }

    protected void setAsyncDisable(boolean asyncDisable) {
        isAsyncDisable = asyncDisable;
    }
}
