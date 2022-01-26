package net.moodssmc.quicksand.mixins.client;

import net.minecraft.client.Camera;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.moodssmc.quicksand.core.ModBlocks;
import net.moodssmc.quicksand.util.CameraExt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Arrays;

@Mixin(Camera.class)
public abstract class CameraMixin implements CameraExt
{
    @Shadow
    private boolean initialized;

    @Shadow
    public abstract Camera.NearPlane getNearPlane();

    @Shadow
    private Vec3 position;

    @Shadow
    private BlockGetter level;

    @Override
    public boolean isFacingQuicksand()
    {
        if(this.initialized)
        {
            Camera.NearPlane plane = this.getNearPlane();

            for(Vec3 vec : Arrays.asList(plane.forward,
                    plane.getTopLeft(),
                    plane.getTopRight(),
                    plane.getBottomLeft(),
                    plane.getBottomRight()))
            {
                Vec3 position = this.position.add(vec);
                BlockPos pos = new BlockPos(position);
                BlockState state = this.level.getBlockState(pos);
                return state.is(ModBlocks.QUICKSAND.get());
            }
        }

        return false;
    }
}
