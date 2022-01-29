package net.moodssmc.quicksand.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.moodssmc.quicksand.Config;
import net.moodssmc.quicksand.Main;
import net.moodssmc.quicksand.api.FogColorable;
import net.moodssmc.quicksand.core.ModTags;
import net.moodssmc.quicksand.util.CameraExt;
import net.moodssmc.quicksand.util.OptifineHelper;

public class ClientEvents
{
    private static final BlockState VOID_AIR = Blocks.VOID_AIR.defaultBlockState();

    @SubscribeEvent
    public void onFogRenderEvent(EntityViewRenderEvent.RenderFogEvent event)
    {
        if(!Config.INSTANCE.disableQuicksandFog.get())
        {
            Camera camera = event.getCamera();
            if(camera != null)
            {
                BlockState facingState = CameraExt.getFacingBlockState(camera);
                if(facingState != VOID_AIR)
                {
                    Block facingBlock = facingState.getBlock();
                    if(!((facingBlock instanceof FogColorable)))
                    {
                        Main.logger().debug("A block has been found with tag {}, but it doesn't extend from {}", ModTags.QUICKSAND, FogColorable.class);
                        return;
                    }

                    float start = 0F;
                    float end = 2F;

                    if (camera.getEntity().isSpectator())
                    {
                        start = -8.0F;
                        end = Minecraft.getInstance().options.renderDistance * 0.5F;
                    }

                    OptifineHelper.setFogNonStandard();
                    RenderSystem.setShaderFogStart(start);
                    RenderSystem.setShaderFogEnd(end);

                    if(OptifineHelper.isShadersEnabled())
                    {
                        OptifineHelper.setShaderFogStart(start);
                        OptifineHelper.setShaderFogEnd(end);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onFogColorsEvent(EntityViewRenderEvent.FogColors event)
    {
        if(!Config.INSTANCE.disableQuicksandFog.get())
        {
            Camera camera = event.getCamera();
            if(camera != null)
            {
                BlockState facingState = CameraExt.getFacingBlockState(camera);
                if(facingState != VOID_AIR)
                {
                    Block facingBlock = facingState.getBlock();
                    if(!((facingBlock instanceof FogColorable)))
                    {
                        Main.logger().debug("A block has been found with tag {}, but it doesn't extend from {}", ModTags.QUICKSAND, FogColorable.class);
                        return;
                    }

                    float[] fogColor = ((FogColorable) facingState.getBlock()).color();

                    float red = fogColor[0] / 0xFF;
                    float green = fogColor[1] / 0xFF;
                    float blue = fogColor[2] / 0xFF;

                    event.setRed(red);
                    event.setGreen(green);
                    event.setBlue(blue);

                    if(OptifineHelper.isShadersEnabled())
                    {
                        OptifineHelper.setShaderFogColor(red, green, blue);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onFieldOfView(EntityViewRenderEvent.FieldOfView event)
    {
        if(!Config.INSTANCE.disableQuicksandFog.get())
        {
            Camera camera = event.getCamera();
            if(camera != null)
            {
                BlockState state = CameraExt.getFacingBlockState(camera);
                if(state != VOID_AIR)
                {
                    double fov = event.getFOV();
                    fov *= Mth.lerp(Minecraft.getInstance().options.fovEffectScale, 1.0F, 0.85714287F);
                    event.setFOV(fov);
                }
            }
        }
    }

}
