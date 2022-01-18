package net.moodssmc.quicksand.core;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.moodssmc.quicksand.Reference;

public class ModLevel extends FeatureUtils
{
    public static final ResourceLocation QUICKSAND_LAKE = new ResourceLocation(Reference.MOD_ID, "quicksand_patch");
    private static final ConfiguredFeature<?, ?> QUICKSAND_LAKE_CONFIGURED_FEATURE = Feature.LAKE.configured(new LakeFeature.Configuration(BlockStateProvider.simple(ModBlocks.QUICKSAND.get()), BlockStateProvider.simple(Blocks.SAND)));
    public static final PlacedFeature QUICKSAND_LAKE_PLACED_FEATURE = QUICKSAND_LAKE_CONFIGURED_FEATURE.placed(RarityFilter.onAverageOnceEvery(28), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

    public static void init()
    {
        BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, QUICKSAND_LAKE, QUICKSAND_LAKE_CONFIGURED_FEATURE);
        BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, QUICKSAND_LAKE, QUICKSAND_LAKE_PLACED_FEATURE);
    }
}
