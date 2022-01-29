package net.moodssmc.quicksand;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.moodssmc.quicksand.client.ClientEvents;
import net.moodssmc.quicksand.core.*;
import net.moodssmc.quicksand.datagen.BlockTagProvider;
import net.moodssmc.quicksand.datagen.EntityTypeTagProvider;
import net.moodssmc.quicksand.datagen.ItemTagProvider;
import net.moodssmc.quicksand.datagen.LootTableProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class Main
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static Logger logger()
    {
        return LOGGER;
    }

    public Main()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.REGISTER.register(bus);
        ModItems.REGISTER.register(bus);
        ModTags.init();
        ModGameRules.init();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.clientSpec);
        bus.addListener(this::onCommonSetup);
        bus.addListener(this::onClientSetup);
        bus.addListener(this::onDataSetup);
    }

    private void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() -> MinecraftForge.EVENT_BUS.register(new ClientEvents()));
    }

    private void onCommonSetup(FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            MinecraftForge.EVENT_BUS.register(new CommonEvents());
            CommonSetup.setup();
        });
    }

    private void onDataSetup(GatherDataEvent event)
    {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        BlockTagProvider blockTagProvider = new BlockTagProvider(dataGenerator, existingFileHelper);
        dataGenerator.addProvider(blockTagProvider);
        dataGenerator.addProvider(new ItemTagProvider(dataGenerator, blockTagProvider, existingFileHelper));
        dataGenerator.addProvider(new EntityTypeTagProvider(dataGenerator, existingFileHelper));
        dataGenerator.addProvider(new LootTableProvider(dataGenerator));
    }
}
