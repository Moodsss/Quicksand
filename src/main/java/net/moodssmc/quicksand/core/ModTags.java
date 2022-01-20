package net.moodssmc.quicksand.core;

import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import net.minecraft.tags.ItemTags;
import net.moodssmc.quicksand.Reference;

public class ModTags
{
    public static final Tag.Named<EntityType<?>> QUICKSAND_WALKABLE_MOBS;
    public static final Tag.Named<EntityType<?>> QUICKSAND_HURTS_EXTRA_TYPES;
    public static final Tag.Named<EntityType<?>> QUICKSAND_HURTS_LESS_TYPES;
    public static final Tag.Named<EntityType<?>> QUICKSAND_IMMUNE_ENTITY_TYPES;

    public static final Tag.Named<Item> QUICKSAND_IMMUNE_WEARABLES;

    static
    {
        QUICKSAND_WALKABLE_MOBS = register("quicksand_walkable_mobs");
        QUICKSAND_HURTS_EXTRA_TYPES = register("quicksand_hurts_extra_types");
        QUICKSAND_HURTS_LESS_TYPES = register("quicksand_hurts_less_types");
        QUICKSAND_IMMUNE_ENTITY_TYPES = register("quicksand_immune_entity_types");
        QUICKSAND_IMMUNE_WEARABLES = ItemTags.bind(Reference.MOD_ID + ":quicksand_immune_wearables");
    }

    protected static Tag.Named<EntityType<?>> register(String name)
    {
        return EntityTypeTags.bind(Reference.MOD_ID + ":" + name);
    }

    @SuppressWarnings("EmptyMethod")
    public static void init()
    {
        //Purposely empty
    }

    private ModTags()
    {
        throw new UnsupportedOperationException();
    }
}
