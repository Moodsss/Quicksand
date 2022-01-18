package net.moodssmc.quicksand.core;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CommonEvents
{
    @SubscribeEvent
    public void onBiomeLoadEvent(BiomeLoadingEvent event)
    {
        if(event.getCategory() == Biome.BiomeCategory.BEACH || event.getCategory() == Biome.BiomeCategory.DESERT)
        {
            event.getGeneration().addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, ModLevel.QUICKSAND_LAKE_PLACED_FEATURE);
        }
    }
}
