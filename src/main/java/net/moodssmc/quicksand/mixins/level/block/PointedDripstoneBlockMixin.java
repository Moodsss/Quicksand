package net.moodssmc.quicksand.mixins.level.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.moodssmc.quicksand.core.ModBlocks;
import net.moodssmc.quicksand.core.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PointedDripstoneBlock.class)
public class PointedDripstoneBlockMixin
{
    @Redirect(method = "scheduleStalactiteFallTicks", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    public boolean onScheduleStalactiteFallTicks$is(BlockState instance, Block block)
    {
        return instance.is(block) || instance.is(ModTags.QUICKSAND);
    }
}
