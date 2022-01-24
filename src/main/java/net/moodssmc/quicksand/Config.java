package net.moodssmc.quicksand;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config
{
    public final ForgeConfigSpec.BooleanValue disableQuicksandFog;

    public Config(ForgeConfigSpec.Builder builder)
    {
        builder.push("client");
        {
            this.disableQuicksandFog = builder
                    .translation("config.quicksand.disableFog")
                    .define("disableQuicksandFog", false);
        }
        builder.pop();
    }

    static final ForgeConfigSpec clientSpec;
    public static final Config INSTANCE;

    static
    {
        final Pair<Config, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Config::new);
        clientSpec = clientSpecPair.getRight();
        INSTANCE = clientSpecPair.getLeft();
    }
}
