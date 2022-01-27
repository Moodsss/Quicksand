package net.moodssmc.quicksand.mixins.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.moodssmc.quicksand.blocks.QuicksandBlock;
import net.moodssmc.quicksand.core.ModBlocks;
import net.moodssmc.quicksand.core.ModDamageSources;
import net.moodssmc.quicksand.core.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin
{
    @Shadow
    public abstract boolean hurt(DamageSource source, float amount);

    @Shadow
    public abstract ItemStack getItemBySlot(EquipmentSlot slot);

    @Shadow
    protected boolean jumping;

    @Shadow
    public abstract boolean isAlive();

    @Redirect(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isInWaterRainOrBubble()Z"))
    public boolean onBaseTick$isInWaterRainOrBubble(LivingEntity instance)
    {
        return this.isInQuicksand || instance.isInWaterRainOrBubble();
    }

    @Inject(method = "handleRelativeFrictionAndCalculateMovement", at = @At("RETURN"), cancellable = true)
    public void onHandleRelativeFrictionAndCalculateMovementRETURN(Vec3 vec, float friction, CallbackInfoReturnable<Vec3> cir)
    {
        Vec3 vec3 = cir.getReturnValue();
        if ((this.horizontalCollision || this.jumping) && (this.getBlockStateOn().is(ModBlocks.QUICKSAND.get())) && QuicksandBlock.canWalkUpon((Entity) (Object) this))
        {
            cir.setReturnValue(new Vec3(vec3.x, 0.2D, vec3.z));
        }
    }

    @Inject(method = "aiStep",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V",
                    ordinal = 4,
                    shift = At.Shift.BEFORE
            )
    )
    public void onAiStep$pop(CallbackInfo ci)
    {
        this.level.getProfiler().push("quicksand$quicksand");
        {
            boolean hurtsExtra = this.getType().is(ModTags.QUICKSAND_HURTS_EXTRA_TYPES);
            boolean hurtsLess = this.getType().is(ModTags.QUICKSAND_HURTS_LESS_TYPES);
            if (!this.level.isClientSide && this.tickCount % 20 == 0 && this.isInQuicksand && canSuffocateFromQuicksand())
            {
                float amount = hurtsExtra ? 5F : (hurtsLess ? 0.5F : 1F);
                this.hurt(ModDamageSources.QUICKSAND, amount);
            }
        }
        this.level.getProfiler().pop();
    }

    @Inject(method = "readAdditionalSaveData", at = @At("RETURN"))
    public void onLoad$readAdditionalSaveData(CompoundTag compound, CallbackInfo ci)
    {
        if(!this.level.isClientSide)
        {
            this.isInQuicksand = compound.getBoolean("isInQuicksand");
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At(value = "RETURN"))
    public void onSaveWithoutId$addAdditionalSaveData(CompoundTag compound, CallbackInfo ci)
    {
        if(!this.level.isClientSide)
        {
            compound.putBoolean("isInQuicksand", this.isInQuicksand);
        }
    }

    @Override
    protected boolean canSuffocateFromQuicksand()
    {
        if(this.isSpectator())
        {
            return false;
        }

        boolean flag =
                !this.getItemBySlot(EquipmentSlot.HEAD).is(ModTags.QUICKSAND_IMMUNE_WEARABLES) &&
                !this.getItemBySlot(EquipmentSlot.CHEST).is(ModTags.QUICKSAND_IMMUNE_WEARABLES) &&
                !this.getItemBySlot(EquipmentSlot.LEGS).is(ModTags.QUICKSAND_IMMUNE_WEARABLES) &&
                !this.getItemBySlot(EquipmentSlot.FEET).is(ModTags.QUICKSAND_IMMUNE_WEARABLES);

        return flag && super.canSuffocateFromQuicksand();
    }
}
