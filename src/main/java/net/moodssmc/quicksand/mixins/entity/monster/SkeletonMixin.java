package net.moodssmc.quicksand.mixins.entity.monster;

import net.minecraft.world.entity.monster.Skeleton;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Skeleton.class)
public abstract class SkeletonMixin extends AbstractSkeletonMixin
{
    @Override
    protected boolean canSuffocateFromQuicksand()
    {
        return false;
    }
}
