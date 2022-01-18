package net.moodssmc.quicksand.mixins.entity;

import net.minecraft.world.entity.Mob;
import net.moodssmc.quicksand.mixins.entity.LivingEntityMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntityMixin
{
    @Redirect(method = "isSunBurnTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;isInWaterRainOrBubble()Z"))
    public boolean onIsSunBurnTick$mobIsInWaterRainOrBubble(Mob instance)
    {
        return this.isInQuicksand || instance.isInWaterRainOrBubble();
    }
}
