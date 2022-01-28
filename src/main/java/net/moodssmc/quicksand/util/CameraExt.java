package net.moodssmc.quicksand.util;

import net.minecraft.client.Camera;
import net.minecraft.world.level.block.state.BlockState;

public interface CameraExt
{
    static BlockState getFacingBlockState(Camera camera)
    {
        return ((CameraExt) camera).getFacingBlockState();
    }

    BlockState getFacingBlockState();
}
