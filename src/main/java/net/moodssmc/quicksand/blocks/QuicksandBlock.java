package net.moodssmc.quicksand.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.moodssmc.quicksand.core.ModBlocks;
import net.moodssmc.quicksand.core.ModTags;
import net.moodssmc.quicksand.core.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Random;

@SuppressWarnings("deprecation")
public class QuicksandBlock extends SandBlock implements BucketPickup
{
    private static final BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();

    private static final VoxelShape FULL_SHAPE = Shapes.block();
    private static final VoxelShape EMPTY_SHAPE = Shapes.empty();
    private static final VoxelShape FALLING_COLLISION_SHAPE = Shapes.box(0D, 0D, 0D, 1D, 0.9D, 1D);

    public QuicksandBlock()
    {
        super(14076051, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND).strength(0.6F).sound(SoundType.SAND).dynamicShape());
    }

    @Override
    public void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Entity entity)
    {
        Random random = level.getRandom();
        if (!(entity instanceof LivingEntity) || entity.getFeetBlockState().is(this))
        {
            entity.makeStuckInBlock(state, new Vec3(0.6F, 0.4D, 0.6F));
            if (level.isClientSide)
            {
                boolean flag = entity.xOld != entity.getX() || entity.zOld != entity.getZ();
                if (flag && random.nextBoolean())
                {
                    level.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, state), entity.getX(), entity.getY() + 1, entity.getZ(), (Mth.randomBetween(random, -1.0F, 1.0F) * 0.083333336F), 0.05F, (Mth.randomBetween(random, -1.0F, 1.0F) * 0.083333336F));
                }
            }
        }

        if (!level.isClientSide)
        {
            if (entity.isOnFire() && entity.mayInteract(level, pos))
            {
                entity.setSharedFlagOnFire(false);
            }
        }
    }

    @Override
    protected void falling(FallingBlockEntity entity)
    {
        entity.dropItem = false;
    }

    @Override
    public void fallOn(@NotNull Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull Entity entity, float velocity)
    {
        if (!(velocity < 4F) && entity instanceof LivingEntity livingentity)
        {
            LivingEntity.Fallsounds fallSounds = livingentity.getFallSounds();
            SoundEvent soundevent = velocity < 7F ? fallSounds.small() : fallSounds.big();
            entity.playSound(soundevent, 1F, 1F);
        }

        super.fallOn(level, state, pos, entity, velocity);
    }

    @Override
    public boolean skipRendering(@NotNull BlockState self, BlockState state, @NotNull Direction direction)
    {
        if(state.is(this))
        {
            return true;
        }

        return super.skipRendering(self, state, direction);
    }

    @Override
    @NotNull
    public VoxelShape getOcclusionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos)
    {
        return EMPTY_SHAPE;
    }

    @Override
    @NotNull
    public VoxelShape getVisualShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext ctx)
    {
        return EMPTY_SHAPE;
    }

    @Override
    @NotNull
    public VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext ctx)
    {
        if (ctx instanceof EntityCollisionContext entityCtx)
        {
            Entity entity = entityCtx.getEntity();
            if (entity != null)
            {
                if (entity.fallDistance > 2.5F)
                {
                    return FALLING_COLLISION_SHAPE;
                }

                boolean flag = entity instanceof FallingBlockEntity;
                if (flag || canWalkUpon(entity) && ctx.isAbove(FULL_SHAPE, pos, false) && !entity.isDescending())
                {
                    return super.getCollisionShape(state, level, pos, ctx);
                }
            }
        }

        return EMPTY_SHAPE;
    }

    @Override
    @NotNull
    public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext ctx)
    {
        return FULL_SHAPE;
    }

    @Override
    @NotNull
    public ItemStack pickupBlock(@NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockState state)
    {
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
        if (!level.isClientSide())
        {
            level.levelEvent(2001, pos, Block.getId(state));
        }

        return new ItemStack(ModItems.QUICKSAND_BUCKET.get());
    }

    @Override
    @NotNull
    public Optional<SoundEvent> getPickupSound()
    {
        return Optional.of(SoundEvents.SAND_BREAK);
    }

    @Override
    public boolean isPathfindable(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull PathComputationType type)
    {
        return true;
    }

    public static boolean canWalkUpon(Entity entity)
    {
        if (entity.getType().is(ModTags.QUICKSAND_WALKABLE_MOBS))
        {
            return true;
        }
        return entity instanceof LivingEntity livingEntity && livingEntity.getItemBySlot(EquipmentSlot.FEET).is(ModTags.QUICKSAND_IMMUNE_WEARABLES);
    }

    //[Vanilla Copy]
    public static boolean isEntityFacingBlock(@NotNull BlockGetter level, @NotNull Entity entity)
    {
        double eyeHeight = entity.getEyeY() - 0.11111111D;

        Entity vehicleEntity = entity.getVehicle();
        if (vehicleEntity instanceof Boat boat)
        {
            if (!boat.isUnderWater() && boat.getBoundingBox().maxY >= eyeHeight && boat.getBoundingBox().minY <= eyeHeight)
            {
                return false;
            }
        }

        return level.getBlockState(mpos.set(entity.getX(), eyeHeight, entity.getZ())).is(ModBlocks.QUICKSAND.get());
    }
}
