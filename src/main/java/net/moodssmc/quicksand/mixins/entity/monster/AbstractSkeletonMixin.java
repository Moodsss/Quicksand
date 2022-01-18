package net.moodssmc.quicksand.mixins.entity.monster;

import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.moodssmc.quicksand.mixins.entity.monster.MonsterMixin;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractSkeleton.class)
public abstract class AbstractSkeletonMixin extends MonsterMixin
{}
