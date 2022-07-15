package net.moodssmc.quicksand.mixins.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.moodssmc.quicksand.core.ModTags;
import net.moodssmc.quicksand.util.EntityExt;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

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

    @Shadow
    public abstract double getEyeY();

    @Shadow
    @Nullable
    public abstract Entity getVehicle();

    @Shadow private Vec3 position;
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
        return instance.is(block) || instance.is(ModTags.QUICKSAND);
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
        double eyeHeight = this.getEyeY() - 0.11111111D;

        Entity vehicleEntity = this.getVehicle();
        if (vehicleEntity instanceof Boat boat)
        {
            if (!boat.isUnderWater() && boat.getBoundingBox().maxY >= eyeHeight && boat.getBoundingBox().minY <= eyeHeight)
            {
                return;
            }
        }

        this.isInQuicksand = this.level.getBlockState(new BlockPos(this.position.x, eyeHeight, this.position.z)).is(ModTags.QUICKSAND);
    }

    @Unique
    protected boolean canSuffocateFromQuicksand()
    {
        return !this.getType().is(ModTags.QUICKSAND_IMMUNE_ENTITY_TYPES);
    }

    @Override
    public boolean isInQuicksand()
    {
        return this.isInQuicksand;
    }
}
