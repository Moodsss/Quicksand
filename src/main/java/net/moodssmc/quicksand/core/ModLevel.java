package net.moodssmc.quicksand.core;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.moodssmc.quicksand.Reference;
import net.moodssmc.quicksand.level.QuicksandPlacementFilter;

public class ModLevel extends FeatureUtils
{
    public static final ResourceLocation QUICKSAND_PATCH = new ResourceLocation(Reference.MOD_ID, "quicksand_patch");
    public static final ResourceLocation RED_QUICKSAND_PATCH = new ResourceLocation(Reference.MOD_ID, "red_quicksand_patch");

    public static final ResourceKey<NormalNoise.NoiseParameters> QUICKSAND_NOISE;

    private static final ConfiguredFeature<?, ?> QUICKSAND_PATCH_CONFIGURED_FEATURE;
    public static final PlacedFeature QUICKSAND_PATCH_PLACED_FEATURE;

    private static final ConfiguredFeature<?, ?> RED_QUICKSAND_PATCH_CONFIGURED_FEATURE;
    public static final PlacedFeature RED_QUICKSAND_PATCH_PLACED_FEATURE;

    public static final PlacementModifierType<QuicksandPlacementFilter> QUICKSAND_FILTER;

    static
    {
        QUICKSAND_NOISE = ResourceKey.create(Registry.NOISE_REGISTRY, new ResourceLocation(Reference.MOD_ID, Reference.MOD_ID));
        QUICKSAND_FILTER = () -> QuicksandPlacementFilter.CODEC;

        //noinspection deprecation
        QUICKSAND_PATCH_CONFIGURED_FEATURE = Feature.LAKE
                .configured(new LakeFeature.Configuration(
                        BlockStateProvider.simple(ModBlocks.QUICKSAND.get()),
                        BlockStateProvider.simple(Blocks.SAND)));

        QUICKSAND_PATCH_PLACED_FEATURE = QUICKSAND_PATCH_CONFIGURED_FEATURE
                .placed(QuicksandPlacementFilter.of(),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome());

        RED_QUICKSAND_PATCH_CONFIGURED_FEATURE = Feature.LAKE
                .configured(new LakeFeature.Configuration(
                        BlockStateProvider.simple(ModBlocks.QUICKSAND_RED.get()),
                        BlockStateProvider.simple(Blocks.RED_SAND)));

        RED_QUICKSAND_PATCH_PLACED_FEATURE = RED_QUICKSAND_PATCH_CONFIGURED_FEATURE
                .placed(QuicksandPlacementFilter.of(),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome());
    }

    public static void init()
    {
        BuiltinRegistries.register(Registry.PLACEMENT_MODIFIERS, QUICKSAND_PATCH, QUICKSAND_FILTER);
        BuiltinRegistries.register(BuiltinRegistries.NOISE, QUICKSAND_NOISE, new NormalNoise.NoiseParameters(6, 1.0, 1.0, 1.0, 1.0));

        BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, QUICKSAND_PATCH, QUICKSAND_PATCH_CONFIGURED_FEATURE);
        BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, RED_QUICKSAND_PATCH, RED_QUICKSAND_PATCH_CONFIGURED_FEATURE);

        BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, QUICKSAND_PATCH, QUICKSAND_PATCH_PLACED_FEATURE);
        BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, RED_QUICKSAND_PATCH, RED_QUICKSAND_PATCH_PLACED_FEATURE);
    }

    private ModLevel()
    {
        throw new UnsupportedOperationException();
    }
}
