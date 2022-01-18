package net.moodssmc.quicksand.core;

import net.minecraft.world.damagesource.DamageSource;

public class ModDamageSources
{
    public static final DamageSource QUICKSAND = new DamageSource("quicksand").bypassArmor().setNoAggro();

    private ModDamageSources()
    {
        throw new UnsupportedOperationException();
    }
}
