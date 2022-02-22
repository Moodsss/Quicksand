package net.moodssmc.quicksand.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.moodssmc.quicksand.core.ModItems;
import org.jetbrains.annotations.NotNull;

public class QuicksandBlock extends AbstractBlock
{
    public QuicksandBlock()
    {
        super(Mth.color(219, 211, 160));
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

        return new ItemStack(ModItems.QUICKSAND_BUCKET.get());
    }
}
