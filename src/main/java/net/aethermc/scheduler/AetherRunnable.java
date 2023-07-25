package net.aethermc.scheduler;

import net.aethermc.AetherMC;

@AetherMC(since = "1.0.0")
@FunctionalInterface
public interface AetherRunnable extends Runnable {

    void run();
}
