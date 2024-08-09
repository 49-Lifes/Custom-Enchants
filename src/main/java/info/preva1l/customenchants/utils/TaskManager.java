package info.preva1l.customenchants.utils;

import info.preva1l.customenchants.CustomEnchants;
import lombok.experimental.UtilityClass;

/**
 * Easy creation of Bukkit Tasks
 */
public final class TaskManager {
    /**
     * Synchronous Tasks
     */
    @UtilityClass
    public class Sync {
        /**
         * Run a synchronous task once. Helpful when needing to run some sync code in an async loop
         * @param runnable The runnable, lambda supported yeh
         */
        public void run(Runnable runnable) {
            CustomEnchants.getInstance().getServer().getScheduler().runTask(CustomEnchants.getInstance(), runnable);
        }

        /**
         * Run a synchronous task forever with a delay between runs.
         * @param runnable The runnable, lambda supported yeh
         * @param interval Time between each run
         */
        public void runTask(Runnable runnable, long interval) {
            CustomEnchants.getInstance().getServer().getScheduler().runTaskTimer(CustomEnchants.getInstance(), runnable, 0L, interval);
        }

        /**
         * Run a synchronous task once with a delay. Helpful when needing to run some sync code in an async loop
         * @param runnable The runnable, lambda supported yeh
         * @param delay Time before running.
         */
        public void runLater(Runnable runnable, long delay) {
            CustomEnchants.getInstance().getServer().getScheduler().runTaskLater(CustomEnchants.getInstance(), runnable, delay);
        }
    }

    /**
     * Asynchronous tasks
     */
    @UtilityClass
    public class Async {
        /**
         * Run an asynchronous task once. Helpful when needing to run some sync code in an async loop
         * @param runnable The runnable, lambda supported yeh
         */
        public void run(Runnable runnable) {
            CustomEnchants.getInstance().getServer().getScheduler().runTaskAsynchronously(CustomEnchants.getInstance(), runnable);
        }

        /**
         * Run an asynchronous task forever with a delay between runs.
         * @param runnable The runnable, lambda supported yeh
         * @param interval Time between each run
         */
        public void runTask(Runnable runnable, long interval) {
            CustomEnchants.getInstance().getServer().getScheduler().runTaskTimerAsynchronously(CustomEnchants.getInstance(), runnable, 0L, interval);
        }

        /**
         * Run an asynchronous task once with a delay. Helpful when needing to run some sync code in an async loop
         * @param runnable The runnable, lambda supported yeh
         * @param delay Time before running.
         */
        public void runLater(Runnable runnable, long delay) {
            CustomEnchants.getInstance().getServer().getScheduler().runTaskLaterAsynchronously(CustomEnchants.getInstance(), runnable, delay);
        }
    }
}