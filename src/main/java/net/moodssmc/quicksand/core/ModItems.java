package net.moodssmc.quicksand.core;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.moodssmc.quicksand.Reference;

public class ModItems
{
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    public static final RegistryObject<Item> QUICKSAND_BUCKET = REGISTER.register("quicksand_bucket",
            () -> new SolidBucketItem(ModBlocks.QUICKSAND.get(), SoundEvents.SAND_PLACE, new Item.Properties().setNoRepair().stacksTo(1).tab(CreativeModeTab.TAB_MISC)) {
                @Override
                public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
                    return false;
                }
            });

    private ModItems()
    {
        throw new UnsupportedOperationException();
    }
}
