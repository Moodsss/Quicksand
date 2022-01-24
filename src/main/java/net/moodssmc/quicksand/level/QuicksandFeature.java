package net.moodssmc.quicksand.level;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.moodssmc.quicksand.core.ModLevel;
import org.jetbrains.annotations.NotNull;

public class QuicksandFeature extends AbstractLazyFeature<LakeFeature.Configuration>
{
    private static final double MIN_THRESHOLD = 0.45D;
    private static final double MAX_THRESHOLD = 0.58D;

    protected final Feature<LakeFeature.Configuration> feature;
    protected long lastUpdate;

    private long prevSeed;
    protected NormalNoise noise;

    public QuicksandFeature()
    {
        super(LakeFeature.Configuration.CODEC);
        this.feature = Feature.LAKE;
    }

    @Override
    protected boolean compute(@NotNull FeaturePlaceContext<LakeFeature.Configuration> ctx)
    {
        BlockPos origin = ctx.origin();
        WorldGenLevel level = ctx.level();
        double noise = getOrUpdateNoise(level).getValue(origin.getX(), origin.getY(), origin.getZ());

        if(noise >= MIN_THRESHOLD && noise <= MAX_THRESHOLD)
        {
            this.lastUpdate = BlockPos.asLong(origin.getX(), level.getMinBuildHeight(), origin.getZ());
            return this.feature.place(ctx);
        }

        return false;
    }

    protected NormalNoise getOrUpdateNoise(WorldGenLevel level)
    {
        long newSeed = level.getSeed();
        if(this.prevSeed != newSeed)
        {
            this.prevSeed = newSeed;
            this.noise = Noises.instantiate(RegistryAccess.builtin().ownedRegistry(Registry.NOISE_REGISTRY).orElseThrow(),
                    WorldgenRandom.Algorithm.XOROSHIRO.newInstance(this.prevSeed).forkPositional(), ModLevel.QUICKSAND_NOISE);
        }
        return this.noise;
    }

    @Override
    protected long getContextLastUpdate()
    {
        return this.lastUpdate;
    }
}
