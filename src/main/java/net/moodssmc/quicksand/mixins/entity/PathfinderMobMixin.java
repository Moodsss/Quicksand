package net.moodssmc.quicksand.mixins.entity;

import net.minecraft.world.entity.PathfinderMob;
import net.moodssmc.quicksand.mixins.entity.MobMixin;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PathfinderMob.class)
public abstract class PathfinderMobMixin extends MobMixin
{}
