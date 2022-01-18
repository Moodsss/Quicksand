package net.moodssmc.quicksand.core;

import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import net.minecraft.tags.ItemTags;

public class ModTags
{
    public static final Tag.Named<EntityType<?>> QUICKSAND_WALKABLE_MOBS;
    public static final Tag.Named<EntityType<?>> QUICKSAND_HURTS_EXTRA_TYPES;
    public static final Tag.Named<EntityType<?>> QUICKSAND_IMMUNE_ENTITY_TYPES;

    public static final Tag.Named<Item> QUICKSAND_IMMUNE_WEARABLES;

    static
    {
        QUICKSAND_WALKABLE_MOBS = EntityTypeTags.bind("quicksand:quicksand_walkable_mobs");
        QUICKSAND_HURTS_EXTRA_TYPES = EntityTypeTags.bind("quicksand:quicksand_hurts_extra_types");
        QUICKSAND_IMMUNE_ENTITY_TYPES = EntityTypeTags.bind("quicksand:quicksand_immune_entity_types");
        QUICKSAND_IMMUNE_WEARABLES = ItemTags.bind("quicksand:quicksand_immune_wearables");
    }

    public static void init()
    {
        //Purposely empty
    }

    private ModTags()
    {
        throw new UnsupportedOperationException();
    }
}
