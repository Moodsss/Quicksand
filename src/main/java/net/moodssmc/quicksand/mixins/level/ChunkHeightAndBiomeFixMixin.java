package net.moodssmc.quicksand.mixins.level;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.util.datafix.fixes.ChunkHeightAndBiomeFix;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Set;

@Mixin(ChunkHeightAndBiomeFix.class)
public class ChunkHeightAndBiomeFixMixin
{
    @Mutable
    @Shadow
    @Final
    private static Set<String> BLOCKS_BEFORE_FEATURE_STATUS;

    static
    {
        Set<String> set = Sets.newHashSet(BLOCKS_BEFORE_FEATURE_STATUS);
        set.add("quicksand:quicksand");
        set.add("quicksand:red_quicksand");
        BLOCKS_BEFORE_FEATURE_STATUS = ImmutableSet.copyOf(set);
    }
}
