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

    public static final RegistryObject<Block> QUICKSAND = REGISTER.register("quicksand", QuicksandBlock::new);
    public static final RegistryObject<Block> QUICKSAND_CAULDRON = REGISTER.register("quicksand_cauldron", QuicksandCauldronBlock::new);

    private ModBlocks()
    {
        throw new UnsupportedOperationException();
    }
}
