package net.moodssmc.quicksand.core;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.moodssmc.quicksand.Reference;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class CommonEvents
{
    @SubscribeEvent
    public static void onBiomeLoadEvent(BiomeLoadingEvent event)
    {
        if(event.getCategory() == Biome.BiomeCategory.BEACH || event.getCategory() == Biome.BiomeCategory.DESERT)
        {
            PlacedFeature feature = QuicksandFeatures.QUICKSAND_PATCH_PLACED_FEATURE;

            event.getGeneration().addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, Holder.direct(feature));
        }
        else if(event.getCategory() == Biome.BiomeCategory.MESA)
        {
            PlacedFeature feature = QuicksandFeatures.RED_QUICKSAND_PATCH_PLACED_FEATURE;

            event.getGeneration().addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, Holder.direct(feature));
        }
    }
}
