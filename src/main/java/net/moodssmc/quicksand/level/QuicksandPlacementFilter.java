package net.moodssmc.quicksand.level;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.moodssmc.quicksand.core.ModLevel;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class QuicksandPlacementFilter extends PlacementFilter
{
    private static final QuicksandPlacementFilter INSTANCE = new QuicksandPlacementFilter();
    public static Codec<QuicksandPlacementFilter> CODEC = Codec.unit(() -> INSTANCE);

    protected NormalNoise noise;
    protected long prevSeed;

    public static PlacementFilter of()
    {
        return INSTANCE;
    }

    protected QuicksandPlacementFilter()
    {}

    @Override
    protected boolean shouldPlace(@NotNull PlacementContext ctx, @NotNull Random random, @NotNull BlockPos origin)
    {
        double noise = getOrUpdateNoise(ctx.getLevel()).getValue(origin.getX(), origin.getY(), origin.getZ());

        //noinspection ConditionCoveredByFurtherCondition
        boolean placeable = noise >= 0.45D && noise <= 0.58D || noise >= 0.35D && noise <= 0.6D;

        if(placeable)
        {
            LogManager.getLogger("Quicksand").info("Quicksand spawned at origin {}", origin.toString());
        }

        return placeable;
    }

    protected NormalNoise getOrUpdateNoise(WorldGenLevel level)
    {
        long newSeed = level.getSeed();
        if(this.prevSeed != newSeed)
        {
            this.prevSeed = newSeed;
            this.noise = Noises.instantiate(RegistryAccess.builtinCopy().ownedRegistry(Registry.NOISE_REGISTRY).orElseThrow(),
                    WorldgenRandom.Algorithm.XOROSHIRO.newInstance(this.prevSeed).forkPositional(), ModLevel.QUICKSAND_NOISE);
        }
        return this.noise;
    }

    @Override
    @NotNull
    public PlacementModifierType<?> type()
    {
        return ModLevel.QUICKSAND_FILTER;
    }
}
