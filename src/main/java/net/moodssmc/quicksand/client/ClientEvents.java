package net.moodssmc.quicksand.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.moodssmc.quicksand.Config;
import net.moodssmc.quicksand.util.CameraExt;

public class ClientEvents
{
    private static final float[] FOG_COLOR = new float[] {220F / 255F, 210F / 255F, 165F / 255F};

    @SubscribeEvent
    public void onFogRenderEvent(EntityViewRenderEvent.RenderFogEvent event)
    {
        Camera camera = event.getCamera();
        if(camera != null)
        {
            if(CameraExt.isFacingQuicksand(camera) && !Config.INSTANCE.disableQuicksandFog.get())
            {
                float start = 0F;
                float end = 2F;

                if (camera.getEntity().isSpectator())
                {
                    start = -8.0F;
                    end = Minecraft.getInstance().options.renderDistance * 0.5F;
                }

                RenderSystem.setShaderFogStart(start);
                RenderSystem.setShaderFogEnd(end);
            }
        }
    }

    @SubscribeEvent
    public void onFogColorsEvent(EntityViewRenderEvent.FogColors event)
    {
        Camera camera = event.getCamera();
        if(camera != null)
        {
            if(CameraExt.isFacingQuicksand(camera) && !Config.INSTANCE.disableQuicksandFog.get())
            {
                event.setRed(FOG_COLOR[0]);
                event.setGreen(FOG_COLOR[1]);
                event.setBlue(FOG_COLOR[2]);
            }
        }
    }

    @SubscribeEvent
    public void onFieldOfView(EntityViewRenderEvent.FieldOfView event)
    {
        Camera camera = event.getCamera();
        if(camera != null)
        {
            if(CameraExt.isFacingQuicksand(camera) && !Config.INSTANCE.disableQuicksandFog.get())
            {
                double fov = event.getFOV();
                fov *= Mth.lerp(Minecraft.getInstance().options.fovEffectScale, 1.0F, 0.85714287F);
                event.setFOV(fov);
            }
        }
    }
}
