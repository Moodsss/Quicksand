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
import net.moodssmc.quicksand.items.BucketItem;

public class ModItems
{
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    public static final RegistryObject<Item> QUICKSAND_BUCKET = REGISTER.register("quicksand_bucket",
            () -> new BucketItem(ModBlocks.QUICKSAND.get()));

    public static final RegistryObject<Item> RED_QUICKSAND_BUCKET = REGISTER.register("red_quicksand_bucket",
            () -> new BucketItem(ModBlocks.RED_QUICKSAND.get()));

    private ModItems()
    {
        throw new UnsupportedOperationException();
    }
}
