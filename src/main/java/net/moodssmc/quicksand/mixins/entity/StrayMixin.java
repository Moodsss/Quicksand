package net.moodssmc.quicksand.mixins.entity;

import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.moodssmc.quicksand.core.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Stray.class)
public class StrayMixin
{
    @Redirect(method = "checkStraySpawnRules",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"
            )
    )
    private static boolean onCheckStraySpawnRules$is(BlockState instance, Block block)
    {
        return instance.is(block) || instance.is(ModTags.QUICKSAND);
    }
}
