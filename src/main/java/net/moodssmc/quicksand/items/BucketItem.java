package net.moodssmc.quicksand.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.level.block.Block;

public class BucketItem extends SolidBucketItem
{

    public BucketItem(Block block)
    {
        super(block, SoundEvents.SAND_PLACE, new Item.Properties().setNoRepair().stacksTo(1).tab(CreativeModeTab.TAB_MISC));
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return false;
    }
}
