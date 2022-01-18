package net.moodssmc.quicksand;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.moodssmc.quicksand.client.ClientEvents;
import net.moodssmc.quicksand.core.*;
import net.moodssmc.quicksand.datagen.BlockTagProvider;
import net.moodssmc.quicksand.datagen.EntityTypeTagProvider;
import net.moodssmc.quicksand.datagen.LootTableProvider;

@Mod(Reference.MOD_ID)
public class Main
{

    public Main()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.REGISTER.register(bus);
        ModItems.REGISTER.register(bus);
        ModTags.init();
        ModGameRules.init();
        bus.addListener(this::onCommonSetup);
        bus.addListener(this::onDataSetup);
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        MinecraftForge.EVENT_BUS.register(new CommonEvents());
    }

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
        event.enqueueWork(CommonSetup::setup);
    }

    private void onDataSetup(GatherDataEvent event)
    {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        dataGenerator.addProvider(new BlockTagProvider(dataGenerator, existingFileHelper));
        dataGenerator.addProvider(new EntityTypeTagProvider(dataGenerator, existingFileHelper));
        dataGenerator.addProvider(new LootTableProvider(dataGenerator));
    }
}
