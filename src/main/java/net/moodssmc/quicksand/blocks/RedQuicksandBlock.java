package net.moodssmc.quicksand.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.moodssmc.quicksand.core.ModItems;
import org.jetbrains.annotations.NotNull;

public class RedQuicksandBlock extends AbstractBlock
{
    public RedQuicksandBlock()
    {
        super(Mth.color(169, 88, 33));
    }

    @Override
    @NotNull
    public ItemStack pickupBlock(@NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockState state)
    {
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
        if (!level.isClientSide())
        {
            level.levelEvent(2001, pos, Block.getId(state));
        }

        return new ItemStack(ModItems.RED_QUICKSAND_BUCKET.get());
    }
}
