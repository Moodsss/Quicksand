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
            () -> new QuicksandBlock(new float[] {219, 211, 160}));
    public static final RegistryObject<Block> RED_QUICKSAND = REGISTER.register("red_quicksand",
            () -> new QuicksandBlock(new float[] {169, 88, 33}));

    public static final RegistryObject<Block> QUICKSAND_CAULDRON = REGISTER.register("quicksand_cauldron",
            () -> new QuicksandCauldronBlock(QuicksandCauldronInteraction.QUICKSAND));
    public static final RegistryObject<Block> RED_QUICKSAND_CAULDRON = REGISTER.register("red_quicksand_cauldron",
            () -> new QuicksandCauldronBlock(QuicksandCauldronInteraction.RED_QUICKSAND));

    private ModBlocks()
    {
        throw new UnsupportedOperationException();
    }
}
