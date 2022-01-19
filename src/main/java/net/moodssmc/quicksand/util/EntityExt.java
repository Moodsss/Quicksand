package net.moodssmc.quicksand.util;

import net.minecraft.world.entity.Entity;

public interface EntityExt
{
    static boolean isInQuicksand(Entity entity)
    {
        return ((EntityExt) entity).isInQuicksand();
    }

    boolean isInQuicksand();
}
