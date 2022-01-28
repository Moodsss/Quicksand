package net.moodssmc.quicksand.core;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.moodssmc.quicksand.Reference;
import net.moodssmc.quicksand.blocks.QuicksandBlock;
import net.moodssmc.quicksand.blocks.QuicksandCauldronBlock;

public class ModBlocks
{
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);

    public static final RegistryObject<Block> QUICKSAND = REGISTER.register("quicksand",
            () -> new QuicksandBlock(14406560, new float[] {220F / 255F, 210F / 255F, 165F / 255F}));
    public static final RegistryObject<Block> RED_QUICKSAND = REGISTER.register("red_quicksand",
            () -> new QuicksandBlock(11098145, new float[] {220F / 255F, 195F / 255F, 140F / 255F}));

    public static final RegistryObject<Block> QUICKSAND_CAULDRON = REGISTER.register("quicksand_cauldron",
            () -> new QuicksandCauldronBlock(QuicksandCauldronInteraction.QUICKSAND));
    public static final RegistryObject<Block> RED_QUICKSAND_CAULDRON = REGISTER.register("red_quicksand_cauldron",
            () -> new QuicksandCauldronBlock(QuicksandCauldronInteraction.RED_QUICKSAND));

    private ModBlocks()
    {
        throw new UnsupportedOperationException();
    }
}
