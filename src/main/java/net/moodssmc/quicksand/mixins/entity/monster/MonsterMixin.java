package net.moodssmc.quicksand.mixins.entity.monster;

import net.minecraft.world.entity.monster.Monster;
import net.moodssmc.quicksand.mixins.entity.PathfinderMobMixin;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Monster.class)
public abstract class MonsterMixin extends PathfinderMobMixin
{}
