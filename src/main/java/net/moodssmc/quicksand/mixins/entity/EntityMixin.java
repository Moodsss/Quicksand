package net.moodssmc.quicksand.mixins.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.moodssmc.quicksand.blocks.QuicksandBlock;
import net.moodssmc.quicksand.core.ModBlocks;
import net.moodssmc.quicksand.core.ModTags;
import net.moodssmc.quicksand.util.EntityExt;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntityExt
{
    @Shadow
    public Level level;

    @Shadow
    public abstract EntityType<?> getType();

    @Shadow
    public int tickCount;

    @Shadow
    public abstract boolean isSpectator();

    @Shadow
    protected abstract BlockState getBlockStateOn();

    @Shadow
    public boolean horizontalCollision;

    @Shadow
    @Final
    protected SynchedEntityData entityData;

    @Shadow
    public abstract boolean isSilent();

    @Shadow
    public abstract BlockPos blockPosition();

    @Shadow
    public abstract SynchedEntityData getEntityData();

    @Unique
    protected boolean isInQuicksand;

    @Redirect(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isInWaterRainOrBubble()Z"))
    public boolean onMove$isEntityInWaterRainOrBubble(Entity instance)
    {
        return this.isInQuicksand || instance.isInWaterRainOrBubble();
    }

    @Redirect(method = "move",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z",
                    ordinal = 0
            )
    )
    public boolean onMove$is(BlockState instance, Block block)
    {
        return instance.is(block) || instance.is(ModBlocks.QUICKSAND.get());
    }

    @Inject(method = "baseTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;updateInWaterStateAndDoFluidPushing()Z",
                    shift = At.Shift.BEFORE
            )
    )
    public void onBaseTick$updateInWaterStateAndDoFluidPushing(CallbackInfo ci)
    {
        this.isInQuicksand = QuicksandBlock.isEntityFacingBlock(((Entity) (Object) this));
    }

    @Unique
    protected boolean canSuffocateFromQuicksand()
    {
        return !ModTags.QUICKSAND_IMMUNE_ENTITY_TYPES.contains(this.getType());
    }

    @Override
    public boolean isInQuicksand()
    {
        return this.isInQuicksand;
    }
}
