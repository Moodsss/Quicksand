package net.moodssmc.quicksand.datagen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.ForgeRegistries;
import net.moodssmc.quicksand.Reference;
import net.moodssmc.quicksand.core.ModBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LootTableProvider extends net.minecraft.data.loot.LootTableProvider
{
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> tables =
            ImmutableList.of(Pair.of(BlockProvider::new, LootContextParamSets.BLOCK));

    public LootTableProvider(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    @NotNull
    public List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables()
    {
        return this.tables;
    }

    @Override
    protected void validate(@NotNull Map<ResourceLocation, LootTable> map, @NotNull ValidationContext context) {}

    private static class BlockProvider extends BlockLoot
    {
        @Override
        protected void addTables()
        {
            this.dropOther(ModBlocks.QUICKSAND_CAULDRON.get(), Blocks.CAULDRON);
            this.dropOther(ModBlocks.RED_QUICKSAND_CAULDRON.get(), Blocks.CAULDRON);
            this.add(ModBlocks.QUICKSAND.get(), noDrop());
            this.add(ModBlocks.RED_QUICKSAND.get(), noDrop());
        }

        @Override
        @NotNull
        protected Iterable<Block> getKnownBlocks()
        {
            return ForgeRegistries.BLOCKS.getValues().stream().filter(entityType -> entityType.getRegistryName() != null && Reference.MOD_ID.equals(entityType.getRegistryName().getNamespace())).collect(Collectors.toSet());
        }
    }
}
