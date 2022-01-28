package net.moodssmc.quicksand.core;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Map;

public interface QuicksandCauldronInteraction extends CauldronInteraction
{
    CauldronInteraction FILL_QUICKSAND = (state, world, pos, player, hand, stack) ->
            CauldronInteraction.emptyBucket(world, pos, player, hand, stack, ModBlocks.QUICKSAND_CAULDRON.get().defaultBlockState(), SoundEvents.SAND_PLACE);

    CauldronInteraction FILL_RED_QUICKSAND = (state, world, pos, player, hand, stack) ->
            CauldronInteraction.emptyBucket(world, pos, player, hand, stack, ModBlocks.RED_QUICKSAND_CAULDRON.get().defaultBlockState(), SoundEvents.SAND_PLACE);

    CauldronInteraction EMPTY_QUICKSAND = (state, world, pos, player, hand, stack) ->
            CauldronInteraction.fillBucket(state, world, pos, player, hand, stack, new ItemStack(ModItems.QUICKSAND_BUCKET.get()), blockState -> true, SoundEvents.SAND_BREAK);

    CauldronInteraction EMPTY_RED_QUICKSAND = (state, world, pos, player, hand, stack) ->
            CauldronInteraction.fillBucket(state, world, pos, player, hand, stack, new ItemStack(ModItems.RED_QUICKSAND_BUCKET.get()), blockState -> true, SoundEvents.SAND_BREAK);

    Map<Item, CauldronInteraction> QUICKSAND = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> RED_QUICKSAND = CauldronInteraction.newInteractionMap();

    static void init()
    {
        EMPTY.put(ModItems.QUICKSAND_BUCKET.get(), FILL_QUICKSAND);
        WATER.put(ModItems.QUICKSAND_BUCKET.get(), FILL_QUICKSAND);
        LAVA.put(ModItems.QUICKSAND_BUCKET.get(), FILL_QUICKSAND);
        POWDER_SNOW.put(ModItems.QUICKSAND_BUCKET.get(), FILL_QUICKSAND);

        EMPTY.put(ModItems.RED_QUICKSAND_BUCKET.get(), FILL_RED_QUICKSAND);
        WATER.put(ModItems.RED_QUICKSAND_BUCKET.get(), FILL_RED_QUICKSAND);
        LAVA.put(ModItems.RED_QUICKSAND_BUCKET.get(), FILL_RED_QUICKSAND);
        POWDER_SNOW.put(ModItems.RED_QUICKSAND_BUCKET.get(), FILL_RED_QUICKSAND);

        QUICKSAND.put(Items.BUCKET, EMPTY_QUICKSAND);
        RED_QUICKSAND.put(Items.BUCKET, EMPTY_RED_QUICKSAND);

        CauldronInteraction.addDefaultInteractions(QUICKSAND);
        CauldronInteraction.addDefaultInteractions(RED_QUICKSAND);
    }
}
