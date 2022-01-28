package net.moodssmc.quicksand.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@SuppressWarnings("deprecation")
public class QuicksandCauldronBlock extends AbstractCauldronBlock
{
    public QuicksandCauldronBlock(Map<Item, CauldronInteraction> interaction)
    {
        super(BlockBehaviour.Properties.copy(Blocks.CAULDRON), interaction);
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
