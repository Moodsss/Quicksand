package net.moodssmc.quicksand.core;

import net.minecraft.world.level.GameRules;

public class ModGameRules
{
    public static final GameRules.Key<GameRules.BooleanValue> RULE_QUICKSAND_DAMAGE;

    static
    {
        RULE_QUICKSAND_DAMAGE = GameRules.register("quicksandDamage", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));
    }

    public static void init()
    {
        //Purposely empty
    }

    private ModGameRules()
    {
        throw new UnsupportedOperationException();
    }
}
