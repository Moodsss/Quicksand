package net.moodssmc.quicksand.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class CommonSetup
{
    public static void setup()
    {
        QuicksandCauldronInteraction.init();
        ModLevel.init();
        MinecraftForge.EVENT_BUS.register(new CommonSetup());

        DispenseItemBehavior behavior = new DispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            @Override
            @NotNull
            public ItemStack dispense(@NotNull BlockSource source, @NotNull ItemStack stack)
            {
                DispensibleContainerItem dispensibleItem = (DispensibleContainerItem) stack.getItem();

                Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
                BlockPos pos = source.getPos().relative(direction);
                Level level = source.getLevel();

                if (dispensibleItem.emptyContents(null, level, pos, null))
                {
                    dispensibleItem.checkExtraContent(null, level, stack, pos);
                    return new ItemStack(Items.BUCKET, 1, stack.getTag());
                }

                return defaultDispenseItemBehavior.dispense(source, stack);
            }
        };

        DispenserBlock.registerBehavior(ModItems.QUICKSAND_BUCKET::get, behavior);
        DispenserBlock.registerBehavior(ModItems.RED_QUICKSAND_BUCKET::get, behavior);
    }

    @SubscribeEvent
    public void onBiomeLoadEvent(BiomeLoadingEvent event)
    {
        if(event.getCategory() == Biome.BiomeCategory.BEACH || event.getCategory() == Biome.BiomeCategory.DESERT)
        {
            event.getGeneration().addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModLevel.QUICKSAND_PATCH_PLACED_FEATURE);
        }
        else if(event.getCategory() == Biome.BiomeCategory.MESA)
        {
            event.getGeneration().addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModLevel.RED_QUICKSAND_PATCH_PLACED_FEATURE);
        }
    }
}
