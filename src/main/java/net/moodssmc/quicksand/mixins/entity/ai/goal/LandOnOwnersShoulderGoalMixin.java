package net.moodssmc.quicksand.mixins.entity.ai.goal;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.goal.LandOnOwnersShoulderGoal;
import net.moodssmc.quicksand.util.EntityExt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LandOnOwnersShoulderGoal.class)
public class LandOnOwnersShoulderGoalMixin
{
    @Redirect(method = "canUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;isInWater()Z"))
    public boolean onCanUse$isInWater(ServerPlayer instance)
    {
        return instance.isInWater() && EntityExt.isInQuicksand(instance);
    }
}
