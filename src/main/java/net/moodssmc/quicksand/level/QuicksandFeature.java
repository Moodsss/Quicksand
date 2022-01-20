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
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

public class QuicksandFeature extends AbstractLazyFeature<LakeFeature.Configuration>
{
    private static final double MIN_THRESHOLD = 0.45D;
    private static final double MAX_THRESHOLD = 0.58D;

    protected final Feature<LakeFeature.Configuration> feature;
    protected long lastUpdate;

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
        double noise = createNoise(level).getValue(origin.getX(), origin.getY(), origin.getZ());

        if(noise >= MIN_THRESHOLD && noise <= MAX_THRESHOLD)
        {
            LogManager.getLogger().info("Quicksand patch spawned in at: {}", origin);
            this.lastUpdate = BlockPos.asLong(origin.getX(), level.getMinBuildHeight(), origin.getZ());
            return this.feature.place(ctx);
        }

        return false;
    }

    protected static NormalNoise createNoise(WorldGenLevel level)
    {
        return Noises.instantiate(RegistryAccess.builtin().ownedRegistry(Registry.NOISE_REGISTRY).orElseThrow(),
                WorldgenRandom.Algorithm.XOROSHIRO.newInstance(level.getSeed()).forkPositional(), ModLevel.QUICKSAND_NOISE);
    }

    @Override
    protected long getContextLastUpdate()
    {
        return this.lastUpdate;
    }
}
