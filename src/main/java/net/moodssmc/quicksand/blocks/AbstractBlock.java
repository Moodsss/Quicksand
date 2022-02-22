package net.moodssmc.quicksand.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.moodssmc.quicksand.core.QuicksandMaterial;
import net.moodssmc.quicksand.util.EntityHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;

@SuppressWarnings("deprecation")
public abstract class AbstractBlock extends SandBlock implements BucketPickup
{
    private static final VoxelShape FULL_SHAPE = Shapes.block();
    private static final VoxelShape EMPTY_SHAPE = Shapes.empty();
    private static final VoxelShape FALLING_COLLISION_SHAPE = Shapes.box(0D, 0D, 0D, 1D, 0.9D, 1D);

    protected AbstractBlock(int color)
    {
        super(color, BlockBehaviour.Properties.of(QuicksandMaterial.INSTANCE).strength(0.6F).sound(SoundType.SAND).dynamicShape());
    }

    @Override
    public void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Entity entity)
    {
        Random random = level.getRandom();
        if (!(entity instanceof LivingEntity) || entity.getFeetBlockState().is(this))
        {
            entity.makeStuckInBlock(state, new Vec3(0.9F, 1.5D, 0.9F));
            if (level.isClientSide)
            {
                boolean flag = entity.xOld != entity.getX() || entity.zOld != entity.getZ();
                if (flag && random.nextBoolean())
                {
                    level.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, state).setPos(new BlockPos(entity.getX(), entity.getY() + 1, entity.getZ())), entity.getX(), entity.getY() + 1, entity.getZ(), (Mth.randomBetween(random, -1.0F, 1.0F) * 0.083333336F), 0.05F, (Mth.randomBetween(random, -1.0F, 1.0F) * 0.083333336F));
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
                if (flag || EntityHelper.canWalkUpon(entity) && entityCtx.isAbove(FULL_SHAPE, pos, false) && !entity.isDescending())
                {
                    return super.getCollisionShape(state, level, pos, entityCtx);
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
    public Optional<SoundEvent> getPickupSound()
    {
        return Optional.of(SoundEvents.SAND_BREAK);
    }

    @Nullable
    @Override
    public BlockPathTypes getAiPathNodeType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity)
    {
        return BlockPathTypes.POWDER_SNOW;
    }

    @Override
    public boolean isScaffolding(BlockState state, LevelReader world, BlockPos pos, LivingEntity entity)
    {
        return false;
    }

    @Override
    public boolean isSlimeBlock(BlockState state)
    {
        return false;
    }

    @Override
    public boolean isStickyBlock(BlockState state)
    {
        return false;
    }

    @Override
    public boolean isPortalFrame(BlockState state, BlockGetter world, BlockPos pos)
    {
        return false;
    }

    @Override
    public boolean isPathfindable(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull PathComputationType type)
    {
        return true;
    }
}
