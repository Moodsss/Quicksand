package net.moodssmc.quicksand.core;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature.Configuration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.moodssmc.quicksand.Reference;

import java.util.Collections;

public class QuicksandFeatures
{
    public static final ResourceLocation QUICKSAND_PATCH = new ResourceLocation(Reference.MOD_ID, "quicksandPatch");
    public static final ResourceLocation RED_QUICKSAND_PATCH = new ResourceLocation(Reference.MOD_ID, "redQuicksandPatch");

    public static final ConfiguredFeature<Configuration, ?> QUICKSAND_PATCH_CONFIGURED_FEATURE;
    public static final PlacedFeature QUICKSAND_PATCH_PLACED_FEATURE;

    public static final ConfiguredFeature<Configuration, ?> RED_QUICKSAND_PATCH_CONFIGURED_FEATURE;
    public static final PlacedFeature RED_QUICKSAND_PATCH_PLACED_FEATURE;

    static
    {
        QUICKSAND_PATCH_CONFIGURED_FEATURE = new ConfiguredFeature<>(Feature.LAKE, new Configuration(BlockStateProvider.simple(ModBlocks.QUICKSAND.get()), BlockStateProvider.simple(Blocks.SAND)));
        RED_QUICKSAND_PATCH_CONFIGURED_FEATURE = new ConfiguredFeature<>(Feature.LAKE, new Configuration(BlockStateProvider.simple(ModBlocks.RED_QUICKSAND.get()), BlockStateProvider.simple(Blocks.RED_SAND)));

        QUICKSAND_PATCH_PLACED_FEATURE = new PlacedFeature(Holder.direct(QUICKSAND_PATCH_CONFIGURED_FEATURE), Collections.singletonList(BiomeFilter.biome()));
        RED_QUICKSAND_PATCH_PLACED_FEATURE = new PlacedFeature(Holder.direct(RED_QUICKSAND_PATCH_CONFIGURED_FEATURE), Collections.singletonList(BiomeFilter.biome()));
    }

    public static void init()
    {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, QUICKSAND_PATCH, QUICKSAND_PATCH_CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, RED_QUICKSAND_PATCH, RED_QUICKSAND_PATCH_CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, RED_QUICKSAND_PATCH, RED_QUICKSAND_PATCH_PLACED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, QUICKSAND_PATCH, QUICKSAND_PATCH_PLACED_FEATURE);
    }
}
