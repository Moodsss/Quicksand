package net.moodssmc.quicksand.level;

import com.google.common.base.Preconditions;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class AbstractLazyFeature<FC extends FeatureConfiguration> extends Feature<FC>
{
    private long lastUpdate;
    @Nullable
    Boolean result;

    public AbstractLazyFeature(Codec<FC> codec)
    {
        super(codec);
        this.lastUpdate = this.getContextLastUpdate() - 1L;
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<FC> ctx)
    {
        long lastUpdate = this.getContextLastUpdate();
        if (lastUpdate == this.lastUpdate)
        {
            Preconditions.checkState(this.result != null, "Update triggered but the result is null");
        }

        this.lastUpdate = lastUpdate;
        this.result = this.compute(ctx);
        return this.result;
    }

    protected abstract boolean compute(@NotNull FeaturePlaceContext<FC> ctx);

    protected abstract long getContextLastUpdate();
}
