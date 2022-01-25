package net.moodssmc.quicksand.mixins.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.moodssmc.quicksand.mixins.entity.LivingEntityMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.annotation.Nullable;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntityMixin
{
    @Shadow
    @Nullable
    public abstract <T extends Mob> T convertTo(EntityType<T> type, boolean value);

    @Shadow
    public abstract boolean isNoAi();

    @Redirect(method = "isSunBurnTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;isInWaterRainOrBubble()Z"))
    public boolean onIsSunBurnTick$mobIsInWaterRainOrBubble(Mob instance)
    {
        return this.isInQuicksand || instance.isInWaterRainOrBubble();
    }
}
