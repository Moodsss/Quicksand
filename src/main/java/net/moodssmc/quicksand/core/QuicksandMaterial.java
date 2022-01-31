package net.moodssmc.quicksand.core;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class QuicksandMaterial
{
    public static final Material INSTANCE = new Material.Builder(MaterialColor.SAND).nonSolid().noCollider().build();
}
