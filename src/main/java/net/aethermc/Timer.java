package net.aethermc;

import java.util.function.Consumer;
import java.util.function.Supplier;

@AetherMC(since = "1.0.0")
public class Timer {

    private long start;
    private long end;

    public Timer start(Runnable runnable) {
        start = System.currentTimeMillis();
        runnable.run();
        end = System.currentTimeMillis();
        return this;
    }

    public long get() {
        return end - start;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }
}
