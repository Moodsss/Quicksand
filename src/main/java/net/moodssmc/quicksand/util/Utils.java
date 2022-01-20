package net.moodssmc.quicksand.util;

import java.util.concurrent.CompletableFuture;

public class Utils
{
    public static void runIf(boolean value, Runnable runnable)
    {
        if(value)
        {
            CompletableFuture.runAsync(runnable);
        }
    }
}
