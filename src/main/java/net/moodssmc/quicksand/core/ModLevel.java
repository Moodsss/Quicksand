package net.moodssmc.quicksand.core;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.moodssmc.quicksand.Reference;
import net.moodssmc.quicksand.level.QuicksandFeature;

public class ModLevel extends FeatureUtils
{
    public static final ResourceLocation QUICKSAND_PATCH = new ResourceLocation(Reference.MOD_ID, "quicksand_patch");
    public static final ResourceKey<NormalNoise.NoiseParameters> QUICKSAND_NOISE;
    private static final QuicksandFeature QUICKSAND_PATCH_FEATURE;

    private static final ConfiguredFeature<?, ?> QUICKSAND_PATCH_CONFIGURED_FEATURE;
    public static final PlacedFeature QUICKSAND_PATCH_PLACED_FEATURE;

    static
    {
        QUICKSAND_NOISE = ResourceKey.create(Registry.NOISE_REGISTRY, new ResourceLocation(Reference.MOD_ID, Reference.MOD_ID));

        QUICKSAND_PATCH_FEATURE = new QuicksandFeature();

        QUICKSAND_PATCH_CONFIGURED_FEATURE = QUICKSAND_PATCH_FEATURE
                .configured(new LakeFeature.Configuration(
                        BlockStateProvider.simple(ModBlocks.QUICKSAND.get()),
                        BlockStateProvider.simple(Blocks.SAND)));

        QUICKSAND_PATCH_PLACED_FEATURE = QUICKSAND_PATCH_CONFIGURED_FEATURE
                .placed(RarityFilter.onAverageOnceEvery(28),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome());
    }

    public static void init()
    {
        BuiltinRegistries.register(BuiltinRegistries.NOISE, QUICKSAND_NOISE, new NormalNoise.NoiseParameters(6, 1.0, 1.0, 1.0, 1.0));
        BuiltinRegistries.register(Registry.FEATURE, QUICKSAND_PATCH, QUICKSAND_PATCH_FEATURE);
        BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, QUICKSAND_PATCH, QUICKSAND_PATCH_CONFIGURED_FEATURE);
        BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, QUICKSAND_PATCH, QUICKSAND_PATCH_PLACED_FEATURE);
    }

    private ModLevel()
    {
        throw new UnsupportedOperationException();
    }
}
