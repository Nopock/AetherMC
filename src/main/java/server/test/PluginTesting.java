package server.test;

import net.aethermc.plugins.AetherPlugin;
import net.aethermc.scheduler.AetherScheduler;

public class PluginTesting extends AetherPlugin {

    @Override
    public void onEnable() {
        AetherScheduler.scheduleTaskLaterAsync(() -> {
            System.out.println("Hello, world!");
        }, 20);
    }

    @Override
    public void onDisable() {

    }
}
