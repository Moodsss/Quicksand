package net.moodssmc.quicksand.mixins.entity.monster;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.moodssmc.quicksand.util.Shakable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zombie.class)
public abstract class ZombieMixin extends MonsterMixin implements Shakable
{
    @Shadow
    public abstract boolean isUnderWaterConverting();

    @Unique
    private static final EntityDataAccessor<Boolean> DATA_HUSK_CONVERSION_ID = SynchedEntityData.defineId(Zombie.class, EntityDataSerializers.BOOLEAN);

    @Unique
    private static final String HUSK_CONVERSION_TAG = "HuskConversionTime";

    @Unique
    private int huskConversionTime, inQuicksandTime;

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTickHEAD(CallbackInfo ci)
    {
        if (!this.level.isClientSide && this.isAlive() && !this.isNoAi())
        {
            if (this.isHuskConverting())
            {
                this.huskConversionTime--;
                if (this.huskConversionTime < 0)
                {
                    this.doHuskConversion();
                }
            }
            else if (this.isInQuicksand)
            {
                this.inQuicksandTime++;
                if (this.inQuicksandTime >= 140)
                {
                    this.startHuskConversion(300);
                }
            }
            else
            {
                this.inQuicksandTime = -1;
            }
        }
    }

    @Inject(method = "defineSynchedData", at = @At("RETURN"))
    public void onDefineSynchedDataRETURN(CallbackInfo ci)
    {
        this.getEntityData().define(DATA_HUSK_CONVERSION_ID, false);
    }

    @Override
    public boolean isShaking()
    {
        return this.isUnderWaterConverting() || this.isHuskConverting();
    }

    @Inject(method = "addAdditionalSaveData", at = @At("RETURN"))
    public void onAddAdditionalSaveDataRETURN(CompoundTag tag, CallbackInfo ci)
    {
        tag.putInt(HUSK_CONVERSION_TAG, this.isHuskConverting() ? this.huskConversionTime : Integer.MIN_VALUE);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("RETURN"))
    public void onReadAdditionalSaveDataRETURN(CompoundTag tag, CallbackInfo ci)
    {
        if (tag.contains(HUSK_CONVERSION_TAG, 99))
        {
            int conversionTime = tag.getInt(HUSK_CONVERSION_TAG);
            if(conversionTime > Integer.MIN_VALUE)
            {
                this.startHuskConversion(conversionTime);
            }
        }
    }

    @Unique
    private void startHuskConversion(int conversionTime)
    {
        this.huskConversionTime = conversionTime;
        this.setHuskConverting(true);
    }

    @Unique
    protected void doHuskConversion()
    {
        this.convertTo(EntityType.HUSK, true);
        if (!this.isSilent())
        {
            this.level.levelEvent(1048, this.blockPosition(), 0);
        }
    }

    @Unique
    protected void setHuskConverting(boolean value)
    {
        this.entityData.set(DATA_HUSK_CONVERSION_ID, value);
    }

    @Unique
    protected boolean isHuskConverting()
    {
        return this.getEntityData().get(DATA_HUSK_CONVERSION_ID);
    }

    @Override
    protected boolean canSuffocateFromQuicksand()
    {
        return false;
    }
}
