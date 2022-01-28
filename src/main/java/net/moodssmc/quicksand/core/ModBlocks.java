package net.moodssmc.quicksand.core;

import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.moodssmc.quicksand.Reference;
import net.moodssmc.quicksand.blocks.QuicksandBlock;
import net.moodssmc.quicksand.blocks.QuicksandCauldronBlock;
import net.moodssmc.quicksand.client.ClientEvents;

import static net.moodssmc.quicksand.client.ClientEvents.FOG_COLOR;
import static net.moodssmc.quicksand.client.ClientEvents.RED_FOG_COLOR;

public class ModBlocks
{
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);

    public static final RegistryObject<Block> QUICKSAND = REGISTER.register("quicksand",
            () -> new QuicksandBlock(Mth.color(FOG_COLOR[0], FOG_COLOR[2], FOG_COLOR[2])));
    public static final RegistryObject<Block> RED_QUICKSAND = REGISTER.register("red_quicksand",
            () -> new QuicksandBlock(Mth.color(RED_FOG_COLOR[0], RED_FOG_COLOR[2], RED_FOG_COLOR[2])));

    public static final RegistryObject<Block> QUICKSAND_CAULDRON = REGISTER.register("quicksand_cauldron",
            () -> new QuicksandCauldronBlock(QuicksandCauldronInteraction.QUICKSAND));
    public static final RegistryObject<Block> RED_QUICKSAND_CAULDRON = REGISTER.register("red_quicksand_cauldron",
            () -> new QuicksandCauldronBlock(QuicksandCauldronInteraction.RED_QUICKSAND));

    private ModBlocks()
    {
        throw new UnsupportedOperationException();
    }
}
