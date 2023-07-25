package net.aethermc.scheduler;

import net.aethermc.AetherMC;

@AetherMC(since = "1.0.0")
public interface AsyncAetherRunnable extends AetherRunnable {

    default Thread getThread() {
        return Thread.currentThread();
    }
}
