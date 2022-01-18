package net.moodssmc.quicksand.mixins.entity.player;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.moodssmc.quicksand.core.ModDamageSources;
import net.moodssmc.quicksand.core.ModGameRules;
import net.moodssmc.quicksand.mixins.entity.LivingEntityMixin;
import net.moodssmc.quicksand.util.EntityExt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntityMixin
{
    @Redirect(method = "setEntityOnShoulder", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isInWater()Z"))
    public boolean onSetEntityOnShoulder$isInWater(Player instance)
    {
        return instance.isInWater() || ((EntityExt) instance).isInQuicksand();
    }

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isSleeping()Z"))
    public boolean onAiStep$isSleeping(Player instance)
    {
        return instance.isSleeping() || ((EntityExt) instance).isInQuicksand();
    }

    @Inject(method = "isInvulnerableTo", at = @At("RETURN"), cancellable = true)
    public void onIsInvulnerableToRETURN(DamageSource source, CallbackInfoReturnable<Boolean> cir)
    {
        if(!cir.getReturnValue() && source == ModDamageSources.QUICKSAND)
        {
            cir.setReturnValue(!this.level.getGameRules().getBoolean(ModGameRules.RULE_QUICKSAND_DAMAGE));
        }
    }
}
