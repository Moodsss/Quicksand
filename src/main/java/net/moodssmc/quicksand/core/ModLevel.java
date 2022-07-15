package net.moodssmc.quicksand.core;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature.Configuration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.moodssmc.quicksand.Reference;
import net.moodssmc.quicksand.level.QuicksandPlacementFilter;

public class ModLevel extends FeatureUtils
{
    public static final DeferredRegister<Feature<?>> REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES, Reference.MOD_ID);

    public static final ResourceLocation QUICKSAND_PATCH = new ResourceLocation(Reference.MOD_ID, "quicksand_patch");

    public static final ResourceKey<NormalNoise.NoiseParameters> QUICKSAND_NOISE;

    public static Holder<ConfiguredFeature<Configuration, ?>> QUICKSAND_PATCH_CONFIGURED_FEATURE;
    public static Holder<PlacedFeature> QUICKSAND_PATCH_PLACED_FEATURE;

    public static Holder<ConfiguredFeature<Configuration, ?>> RED_QUICKSAND_PATCH_CONFIGURED_FEATURE;
    public static Holder<PlacedFeature> RED_QUICKSAND_PATCH_PLACED_FEATURE;

    public static final PlacementModifierType<QuicksandPlacementFilter> QUICKSAND_FILTER;

    static
    {
        QUICKSAND_NOISE = ResourceKey.create(Registry.NOISE_REGISTRY, new ResourceLocation(Reference.MOD_ID, Reference.MOD_ID));
        QUICKSAND_FILTER = () -> QuicksandPlacementFilter.CODEC;
    }

    public static void init()
    {
        BuiltinRegistries.register(Registry.PLACEMENT_MODIFIERS, QUICKSAND_PATCH, QUICKSAND_FILTER);
        BuiltinRegistries.register(BuiltinRegistries.NOISE, QUICKSAND_NOISE, new NormalNoise.NoiseParameters(6, 1.0, 1.0, 1.0, 1.0));

        QUICKSAND_PATCH_CONFIGURED_FEATURE = FeatureUtils.register("quicksandPatch", Feature.LAKE, new Configuration(BlockStateProvider.simple(ModBlocks.QUICKSAND.get()), BlockStateProvider.simple(Blocks.SAND)));
        QUICKSAND_PATCH_PLACED_FEATURE = PlacementUtils.register("quicksandPatch", QUICKSAND_PATCH_CONFIGURED_FEATURE, QuicksandPlacementFilter.of());

        RED_QUICKSAND_PATCH_CONFIGURED_FEATURE = FeatureUtils.register("redQuicksandPatch", Feature.LAKE, new Configuration(BlockStateProvider.simple(ModBlocks.QUICKSAND.get()), BlockStateProvider.simple(Blocks.SAND)));

        RED_QUICKSAND_PATCH_PLACED_FEATURE = PlacementUtils.register("redQuicksandPatch", QUICKSAND_PATCH_CONFIGURED_FEATURE, BiomeFilter.biome());
    }
}
