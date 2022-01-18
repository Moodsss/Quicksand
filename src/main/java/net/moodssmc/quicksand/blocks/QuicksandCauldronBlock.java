package net.moodssmc.quicksand.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.moodssmc.quicksand.core.QuicksandCauldronInteraction;
import org.jetbrains.annotations.NotNull;

public class QuicksandCauldronBlock extends AbstractCauldronBlock
{
    public QuicksandCauldronBlock()
    {
        super(BlockBehaviour.Properties.copy(Blocks.CAULDRON), QuicksandCauldronInteraction.INSTANCE);
    }

    @Override
    public boolean isFull(@NotNull BlockState state)
    {
        return true;
    }

    @Override
    protected double getContentHeight(@NotNull BlockState state)
    {
        return 0.9375D;
    }

    @Override
    public int getAnalogOutputSignal(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos)
    {
        return 3;
    }
}
