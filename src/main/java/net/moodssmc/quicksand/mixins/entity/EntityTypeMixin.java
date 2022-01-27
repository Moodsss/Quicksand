package net.moodssmc.quicksand.mixins.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.moodssmc.quicksand.core.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityType.class)
public class EntityTypeMixin
{
    @Inject(method = "isBlockDangerous", at = @At("RETURN"), cancellable = true)
    public void onIsBlockDangerous(BlockState state, CallbackInfoReturnable<Boolean> cir)
    {
        if(state.is(ModBlocks.QUICKSAND.get()))
        {
            if((Object) this == EntityType.HUSK)
            {
                cir.setReturnValue(false);
            }

            cir.setReturnValue(true);
        }
    }
}
