package net.moodssmc.quicksand.level;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import org.jetbrains.annotations.NotNull;

public class LazyNoiseFeature extends AbstractLazyFeature<LakeFeature.Configuration>
{
    public Feature<LakeFeature.Configuration> feature;

    private final double minThreshold, maxThreshold;
    protected long lastUpdate;

    public LazyNoiseFeature(Feature<LakeFeature.Configuration> feature, double minThreshold, double maxThreshold)
    {
        super(LakeFeature.Configuration.CODEC);
        this.feature = feature;
        this.minThreshold = minThreshold;
        this.maxThreshold = maxThreshold;
    }

    @Override
    protected boolean compute(@NotNull FeaturePlaceContext<LakeFeature.Configuration> ctx)
    {
        double noise = Biome.BIOME_INFO_NOISE.getValue(ctx.origin().getX(), ctx.origin().getZ(), false);

        if(noise >= this.minThreshold && noise <= this.maxThreshold)
        {
            this.lastUpdate = ctx.origin().asLong();
            return this.feature.place(ctx);
        }

        return false;
    }

    @Override
    protected long getContextLastUpdate()
    {
        return this.lastUpdate;
    }
}
