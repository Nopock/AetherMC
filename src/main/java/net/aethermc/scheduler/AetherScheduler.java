package net.aethermc.scheduler;

import net.aethermc.AetherMC;
import net.minestom.server.MinecraftServer;
import net.minestom.server.timer.Scheduler;
import net.minestom.server.timer.TaskSchedule;

import java.util.concurrent.CompletableFuture;

@AetherMC(since = "1.0.0")
public class AetherScheduler {

    public static final Scheduler scheduler = MinecraftServer.getSchedulerManager();

    public static void scheduleTask(AetherRunnable runnable) {
        runnable.run();
    }

    public static void scheduleTaskAsync(AsyncAetherRunnable runnable) {
        CompletableFuture.supplyAsync(() -> {
            scheduleTask(runnable);
            return null;
        });
    }

    public static void scheduleTaskLater(AetherRunnable runnable, int delayTicks) {
        scheduler.submitTask(() -> {
            runnable.run();
            return TaskSchedule.tick(delayTicks);
        });
    }

    public static void scheduleTaskLaterAsync(AsyncAetherRunnable runnable, int delayTicks) {
        CompletableFuture.supplyAsync(() -> {
            scheduleTaskLater(runnable, delayTicks);
            return null;
        });
    }
}
