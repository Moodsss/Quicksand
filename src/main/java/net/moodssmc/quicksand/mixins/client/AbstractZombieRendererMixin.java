package net.moodssmc.quicksand.mixins.client;

import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.world.entity.monster.Zombie;
import net.moodssmc.quicksand.util.Shakable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractZombieRenderer.class)
public class AbstractZombieRendererMixin
{
    @Inject(method = "isShaking(Lnet/minecraft/world/entity/monster/Zombie;)Z", at = @At("HEAD"), cancellable = true)
    public void onIsShakingHEAD(Zombie zombie, CallbackInfoReturnable<Boolean> cir)
    {
        cir.setReturnValue(((Shakable) zombie).isShaking());
    }
}
