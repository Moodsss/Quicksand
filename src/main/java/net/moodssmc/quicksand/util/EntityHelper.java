package net.moodssmc.quicksand.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.moodssmc.quicksand.core.ModTags;
import org.jetbrains.annotations.NotNull;

public class EntityHelper
{
    public static boolean canWalkUpon(@NotNull Entity entity)
    {
        if (entity.getType().is(ModTags.QUICKSAND_WALKABLE_MOBS))
        {
            return true;
        }
        return entity instanceof LivingEntity livingEntity && livingEntity.getItemBySlot(EquipmentSlot.FEET).is(ModTags.QUICKSAND_IMMUNE_WEARABLES);
    }

    public static boolean isInsideQuicksand(Entity entity)
    {
        return ((EntityExt) entity).isInQuicksand();
    }
}
