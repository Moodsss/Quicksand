package net.moodssmc.quicksand.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.moodssmc.quicksand.blocks.QuicksandBlock;

public class ClientEvents
{
    @SubscribeEvent
    public void onFogRenderEvent(EntityViewRenderEvent.RenderFogEvent event)
    {
        Entity entity = event.getCamera().getEntity();
        if(QuicksandBlock.isEntityFacingBlock(entity.level, entity))
        {
            float start = 0F;
            float end = 2F;

            if (entity.isSpectator())
            {
                start = -8.0F;
                end = Minecraft.getInstance().getFrameTime() * 0.5F;
            }

            RenderSystem.setShaderFogStart(start);
            RenderSystem.setShaderFogEnd(end);
        }
    }

    @SubscribeEvent
    public void onFogColorsEvent(EntityViewRenderEvent.FogColors event)
    {
        Entity entity = event.getCamera().getEntity();
        if(QuicksandBlock.isEntityFacingBlock(entity.level, entity))
        {
            event.setRed(220F / 255F);
            event.setGreen(210F / 255F);
            event.setBlue(165F / 255F);
        }
    }

    @SubscribeEvent
    public void onFieldOfView(EntityViewRenderEvent.FieldOfView event)
    {
        //noinspection ConstantConditions
        if(event.getCamera().getEntity() != null)
        {
            Entity entity = event.getCamera().getEntity();
            if(QuicksandBlock.isEntityFacingBlock(entity.level, entity))
            {
                double fov = event.getFOV();
                fov *= Mth.lerp(Minecraft.getInstance().options.fovEffectScale, 1.0F, 0.85714287F);
                event.setFOV(fov);
            }
        }
    }
}
