package net.moodssmc.quicksand.core;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.moodssmc.quicksand.Reference;
import net.moodssmc.quicksand.level.QuicksandPlacementFilter;

public class ModLevel
{
    public static final ResourceKey<NormalNoise.NoiseParameters> QUICKSAND_NOISE;

    public static final PlacementModifierType<QuicksandPlacementFilter> QUICKSAND_FILTER;

    static
    {
        QUICKSAND_NOISE = ResourceKey.create(Registry.NOISE_REGISTRY, new ResourceLocation(Reference.MOD_ID, Reference.MOD_ID));
        QUICKSAND_FILTER = () -> QuicksandPlacementFilter.CODEC;
    }

    public static void init()
    {
        BuiltinRegistries.register(Registry.PLACEMENT_MODIFIERS, QuicksandFeatures.QUICKSAND_PATCH, QUICKSAND_FILTER);
        BuiltinRegistries.register(BuiltinRegistries.NOISE, QUICKSAND_NOISE, new NormalNoise.NoiseParameters(6, 1.0, 1.0, 1.0, 1.0));
    }
}
