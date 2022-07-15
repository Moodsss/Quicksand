package net.moodssmc.quicksand.core;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import net.minecraft.world.level.block.Block;
import net.moodssmc.quicksand.Reference;

public class ModTags
{
    public static final TagKey<EntityType<?>> QUICKSAND_WALKABLE_MOBS;
    public static final TagKey<EntityType<?>> QUICKSAND_HURTS_EXTRA_TYPES;
    public static final TagKey<EntityType<?>> QUICKSAND_HURTS_LESS_TYPES;
    public static final TagKey<EntityType<?>> QUICKSAND_IMMUNE_ENTITY_TYPES;

    public static final TagKey<Item> QUICKSAND_IMMUNE_WEARABLES;
    public static final TagKey<Block> QUICKSAND;

    static
    {
        QUICKSAND_WALKABLE_MOBS = register("quicksand_walkable_mobs");
        QUICKSAND_HURTS_EXTRA_TYPES = register("quicksand_hurts_extra_types");
        QUICKSAND_HURTS_LESS_TYPES = register("quicksand_hurts_less_types");
        QUICKSAND_IMMUNE_ENTITY_TYPES = register("quicksand_immune_entity_types");
        QUICKSAND = registerBlock("quicksand");
        QUICKSAND_IMMUNE_WEARABLES = registerItem("quicksand_immune_wearables");
    }

    protected static TagKey<EntityType<?>> register(String name)
    {
        return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(Reference.MOD_ID, name));
    }

    protected static TagKey<Item> registerItem(String name)
    {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(Reference.MOD_ID, name));
    }

    protected static TagKey<Block> registerBlock(String name)
    {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(Reference.MOD_ID, name));
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
