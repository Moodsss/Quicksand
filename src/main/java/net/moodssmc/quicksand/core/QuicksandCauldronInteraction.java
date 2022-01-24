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

    CauldronInteraction EMPTY_QUICKSAND = (state, world, pos, player, hand, stack) ->
            CauldronInteraction.fillBucket(state, world, pos, player, hand, stack, new ItemStack(ModItems.QUICKSAND_BUCKET.get()), blockState -> true, SoundEvents.BUCKET_FILL_POWDER_SNOW);

    Map<Item, CauldronInteraction> INSTANCE = CauldronInteraction.newInteractionMap();

    static void init()
    {
        EMPTY.put(ModItems.QUICKSAND_BUCKET.get(), FILL_QUICKSAND);
        WATER.put(ModItems.QUICKSAND_BUCKET.get(), FILL_QUICKSAND);
        LAVA.put(ModItems.QUICKSAND_BUCKET.get(), FILL_QUICKSAND);
        POWDER_SNOW.put(ModItems.QUICKSAND_BUCKET.get(), FILL_QUICKSAND);

        INSTANCE.put(Items.BUCKET, EMPTY_QUICKSAND);

        CauldronInteraction.addDefaultInteractions(INSTANCE);
    }
}
