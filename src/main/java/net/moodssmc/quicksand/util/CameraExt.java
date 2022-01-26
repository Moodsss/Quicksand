package net.moodssmc.quicksand.util;

import net.minecraft.client.Camera;

public interface CameraExt
{
    static boolean isFacingQuicksand(Camera camera)
    {
        return ((CameraExt) camera).isFacingQuicksand();
    }

    boolean isFacingQuicksand();
}
