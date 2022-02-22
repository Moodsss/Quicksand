package net.moodssmc.quicksand.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.moodssmc.quicksand.Reference;
import net.moodssmc.quicksand.core.ModBlocks;
import net.moodssmc.quicksand.core.ModTags;
import org.jetbrains.annotations.Nullable;

public class BlockTagProvider extends BlockTagsProvider
{

    public BlockTagProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(generator, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags()
    {
        this.tag(BlockTags.CAULDRONS).add(ModBlocks.QUICKSAND_CAULDRON.get()).add(ModBlocks.RED_QUICKSAND_CAULDRON.get());
        this.tag(BlockTags.INSIDE_STEP_SOUND_BLOCKS).add(ModBlocks.QUICKSAND.get()).add(ModBlocks.RED_QUICKSAND.get());
        this.tag(BlockTags.SAND).add(ModBlocks.QUICKSAND.get()).add(ModBlocks.RED_QUICKSAND.get());
        this.tag(Tags.Blocks.SAND_COLORLESS).add(ModBlocks.QUICKSAND.get());
        this.tag(Tags.Blocks.SAND_RED).add(ModBlocks.RED_QUICKSAND.get());
        this.tag(ModTags.QUICKSAND).add(ModBlocks.QUICKSAND.get()).add(ModBlocks.RED_QUICKSAND.get());
    }
}
