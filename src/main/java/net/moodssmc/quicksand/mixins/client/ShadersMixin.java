package net.moodssmc.quicksand.mixins.client;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.moodssmc.quicksand.util.CameraExt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "net.optifine.shaders.Shaders")
public class ShadersMixin
{
    @Shadow(remap = false)
    private static int isEyeInWater;

    @Inject(method = "beginRender",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Camera;getFluidInCamera()Lnet/minecraft/world/level/material/FogType;",
                    shift = At.Shift.AFTER
            )
    )
    private static void onBeginRender$getFluidInCamera(Minecraft minecraft, Camera activeRenderInfo, float partialTicks, long finishTimeNano, CallbackInfo ci)
    {
        if(CameraExt.isFacingQuicksand(activeRenderInfo))
        {
            isEyeInWater = 3;
        }
    }
}
