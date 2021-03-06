package net.moodssmc.quicksand.core;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.moodssmc.quicksand.Reference;
import net.moodssmc.quicksand.blocks.QuicksandBlock;
import net.moodssmc.quicksand.blocks.QuicksandCauldronBlock;
import net.moodssmc.quicksand.blocks.RedQuicksandBlock;

public class ModBlocks
{
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);

    public static final RegistryObject<Block> QUICKSAND = REGISTER.register("quicksand", QuicksandBlock::new);
    public static final RegistryObject<Block> RED_QUICKSAND = REGISTER.register("red_quicksand", RedQuicksandBlock::new);

    public static final RegistryObject<Block> QUICKSAND_CAULDRON = REGISTER.register("quicksand_cauldron",
            () -> new QuicksandCauldronBlock(QuicksandCauldronInteraction.QUICKSAND));
    public static final RegistryObject<Block> RED_QUICKSAND_CAULDRON = REGISTER.register("red_quicksand_cauldron",
            () -> new QuicksandCauldronBlock(QuicksandCauldronInteraction.RED_QUICKSAND));

    private ModBlocks()
    {
        throw new UnsupportedOperationException();
    }
}
